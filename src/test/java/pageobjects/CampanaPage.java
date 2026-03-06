package pageobjects;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import support.util;

import java.util.List;

public class CampanaPage extends util {
    @FindBy(xpath = "(//button[@class='btn-show-nav'])[1]") protected WebElement btnSidebar;
    @FindBy(xpath = "//a[@href='/campaniaList']") protected WebElement btnCampana;
    @FindBy(xpath = "//span[text()='Campañas']") protected WebElement btnMenuCampana;
    @FindBy(xpath = "(//div/input)[1]") protected WebElement txtBuscarPorNombre;
    @FindBy(xpath = "//button[@class='btn-secundario']") protected WebElement btnBuscar;
    @FindBy(xpath = "//a[@title='Agregar']") protected WebElement btnAgregarCampana;
    @FindBy(xpath = "(//mat-label[contains(normalize-space(.),'País')]/ancestor::mat-form-field//mat-select)[2]") protected WebElement cmbPais;
    @FindBy(xpath = "(//mat-label[contains(normalize-space(.),'Empresa')]/ancestor::mat-form-field//mat-select)[2]") protected WebElement cmbEmpresa;
    @FindBy(xpath = "(//mat-label[contains(normalize-space(.),'Permitir visualización por Área')]/ancestor::mat-form-field//mat-select)[1]") protected WebElement cmbArea;
    @FindBy(xpath = "//input[@formcontrolname='validityStart']") protected WebElement txtFechaInicio;
    @FindBy(xpath = "//input[@formcontrolname='validityEnd']") protected WebElement txtFechaFin;
    public CampanaPage() {
        PageFactory.initElements(driver, this);
    }
    public void clicBotonSidebar(){
        wait.until(ExpectedConditions.elementToBeClickable(btnSidebar));
        btnSidebar.click();
    }
    public void clicBotonCampana(){
        wait.until(ExpectedConditions.elementToBeClickable(btnCampana));
        btnCampana.click();
    }
    public void clicBotonMenuCampana(){
        wait.until(ExpectedConditions.elementToBeClickable(btnMenuCampana));
        btnMenuCampana.click();
    }
    public void clicBotonBuscar(){
        wait.until(ExpectedConditions.elementToBeClickable(btnBuscar));
        btnBuscar.click();
    }
    public void clicAgregarCampana() throws InterruptedException {
        wait.until(ExpectedConditions.elementToBeClickable(btnAgregarCampana));
        Thread.sleep(3000);
        btnAgregarCampana.click();
    }

    public void validarCampos(String campos) {
        boolean camposvalido= false;
        String xpath = "//span[normalize-space(text())='" + campos.trim() + "']";

        try {
            WebElement camposElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            System.out.println("Campos: '" + camposElemento.getText() + "'");
            if (camposElemento.isDisplayed() && camposElemento.getText().equals(campos.trim())) {
                camposvalido = true;
            }
        } catch (TimeoutException | NoSuchElementException e) {

        }
        Assert.assertTrue("El nombre del campos: " + campos.trim() + " no es igual o no se encontró.", camposvalido);
    }
    public void validarOpciones(String opcion) {
        boolean opcionValida = false;
        String xpath = "//mat-label[contains(normalize-space(.),'" + opcion.trim() + "')] | " +
                       "//a[contains(normalize-space(.),'" + opcion.trim() + "')]";
        try {
            WebElement elemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            System.out.println("Opción encontrada: '" + elemento.getText() + "'");
            if (elemento.isDisplayed() && elemento.getText().trim().equals(opcion.trim())) {
                opcionValida = true;
            }
        } catch (TimeoutException | NoSuchElementException e) {

        }
        Assert.assertTrue("La opción: " + opcion.trim() + " no se encontró o no coincide.", opcionValida);
    }
    public void validarOpcionesModal(String opcion) {
        String xpath = "//mat-label";
        List<WebElement> labels = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
        boolean encontrado = false;
        for (WebElement label : labels) {
            String texto = label.getText().trim();
            System.out.println("Label encontrado: " + texto);
            if (texto.equalsIgnoreCase(opcion.trim()) && label.isDisplayed()) {
                encontrado = true;
                break;
            }
        }
        Assert.assertTrue("La opción: " + opcion + " no se encontró.", encontrado);
    }
    public void ingresarBuscarPorNombre(String nombre){
        wait.until(ExpectedConditions.visibilityOf(txtBuscarPorNombre));
        txtBuscarPorNombre.sendKeys(nombre);
    }
    public void seleccionarComboPorLabel(String label, String opcion) {

        try {
            // 1. Abrir el combo por su label
            String xpathCombo = "//mat-label[contains(normalize-space(.),'" + label + "')]/ancestor::mat-form-field//mat-select";
            WebElement combo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathCombo)));
            combo.click();

            // 2. Esperar que aparezca la opción
            String xpathOpcion = "//mat-option//span[contains(normalize-space(.),'" + opcion + "')]";
            WebElement opcionElemento = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpathOpcion)));

            // 3. Seleccionar
            opcionElemento.click();

            System.out.println("Seleccionado: " + opcion);

        } catch (Exception e) {
            Assert.fail("No se pudo seleccionar la opción '" + opcion + "' del combo '" + label + "'");
        }
    }
    public void ingresarFechaInicio(String fechaInicio){
        wait.until(ExpectedConditions.visibilityOf(txtFechaInicio));
        txtFechaInicio.sendKeys(fechaInicio);
    }
    public void ingresarFechaFin(String fechaFin){
        wait.until(ExpectedConditions.visibilityOf(txtFechaFin));
        txtFechaFin.sendKeys(fechaFin);
    }
    public void abrirComboPais() {
        wait.until(ExpectedConditions.visibilityOf(cmbPais));
        cmbPais.click();
    }
    public void abrirComboEmpresa() {
        wait.until(ExpectedConditions.visibilityOf(cmbEmpresa));
        cmbEmpresa.click();
    }
    public void abrirComboPertimirArea() {
        wait.until(ExpectedConditions.visibilityOf(cmbArea));
        cmbArea.click();
    }
    public void seleccionarOpcion_1(String item) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']")));
        WebElement opcionItem = driver.findElement(By.xpath("//mat-option//span[text()='" + item.trim() + "']"));
        opcionItem.click();
    }
    public void seleccionarOpcion_2(String item) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@role='listbox']")));
        WebElement opcionItem = driver.findElement(By.xpath("//span[text()='" + item.trim() + "']"));
        opcionItem.click();
    }
}
