package support;

import definitions.hooks;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.List;

public class util extends hooks {

    public static Velocidad slowDriver;
    public static WebDriverWait wait;
    public static JavascriptExecutor js;
    private Alert dialogo;
    private String ventanaOriginal;

    public util() {
        slowDriver = new Velocidad(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(60));
        js = (JavascriptExecutor) driver;
    }
    public void ventanaActiva(){
        Set<String> identificadores = driver.getWindowHandles();
        for (String identificador:identificadores){
            driver.switchTo().window(identificador);
        }
    }
    public void cambiarANuevaPestana(String ventanaOriginal) {
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            if (!handle.equals(ventanaOriginal)) {
                driver.switchTo().window(handle);
                break;
            }
        }
    }
    // JUEGO DE PESTAÑAS
    public void guardarPestanaActual() {
        ventanaOriginal = driver.getWindowHandle();
    }
    public void cambiarALaUltimaPestana() {
        Set<String> handles = driver.getWindowHandles();
        List<String> listaHandles = new ArrayList<>(handles);
        String ultimaVentana = listaHandles.get(listaHandles.size() - 1);
        driver.switchTo().window(ultimaVentana);
    }
    public void volverAPestanaAnterior() {
        if (ventanaOriginal != null) {
            driver.switchTo().window(ventanaOriginal);
        }
    }
    public void ventanaInicial(){
        driver.close();
        driver.switchTo().window("");
    }
    public void manejarNuevaPestana(long segundosEspera) {
        String ventanaPrincipal = driver.getWindowHandle();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        Set<String> todasLasVentanas = driver.getWindowHandles();

        for (String ventana : todasLasVentanas) {
            if (!ventana.equals(ventanaPrincipal)) {
                driver.switchTo().window(ventana);

                try {
                    Thread.sleep(segundosEspera * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Captura.capturarPaso(driver, "Screenshot");
                driver.close();
                break;
            }
        }
        driver.switchTo().window(ventanaPrincipal);
    }
    public void scrollVertical(WebElement scroll){
        js.executeScript("arguments[0].scrollIntoView(true);", scroll);
    }
    public void scrollVerticalTotal() {
        long lastHeight = (long) js.executeScript("return document.body.scrollHeight");
        while (true) {
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
            try {
                Thread.sleep(1200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long newHeight = (long) js.executeScript("return document.body.scrollHeight");
            if (newHeight == lastHeight) {
                break;
            }
            lastHeight = newHeight;
        }
    }
    public void limpiarCampo(WebElement limpiar){
        js.executeScript("arguments[0].value = '';", limpiar);
    }
    public void localizadorBoton(WebElement localizador){
        js.executeScript("arguments[0].click();", localizador);
    }
    public void aceptarDialogo() {
        dialogo = driver.switchTo().alert();
        dialogo.accept();
    }
    public void obtenerTextoDialogo(String mensaje) {
        dialogo = driver.switchTo().alert();
        Assert.assertEquals(mensaje, dialogo.getText());
    }
    public static void evidencias() throws IOException {
        Date fecha = new Date();
        SimpleDateFormat formato = new SimpleDateFormat("ddMMyyy_HHmmssSSS");
        String path = "C:\\Users\\Nando\\Desktop\\DERRAMA MAGISTERIAL\\AUTOMATIZACION\\TEST_MAGISTERIAL_2025\\target\\evidencias\\";

        String nombre = formato.format(fecha)+"_evidencia.jpg";

        File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(path+nombre));
    }
    public void capturarPantallaYAdjuntar(String nombre) throws Exception {
        Robot robot = new Robot();
        Rectangle pantalla = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        BufferedImage imagen = robot.createScreenCapture(pantalla);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(imagen, "png", baos);
        byte[] capturaBytes = baos.toByteArray();

        hooks.escenarioActual.attach(capturaBytes, "image/png", nombre);
    }
    public void aceptarDialogoAbrirWord() throws Exception {
        Robot robot = new Robot();
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
    public static WebDriver descargarArchivo() {
        // Configurar preferencias para descargas
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("download.default_directory", "C:\\Users\\Nando\\Desktop\\ArchivoDescargado");
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("download.directory_upgrade", true);
        chromePrefs.put("safebrowsing.enabled", true);

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("prefs", chromePrefs);

        // Inicializar el driver con las opciones configuradas
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        return driver;
    }
}