package support;

import definitions.hooks;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Captura {
    public static void capturarPaso(WebDriver driver, String descripcion) {
        Scenario escenario = hooks.escenarioActual;
        if (escenario != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                escenario.attach(screenshot, "image/png", descripcion.replaceAll(" ", "_"));
            } catch (Exception e) {
                escenario.log("No se pudo capturar el paso: " + descripcion);
            }
        }
    }
}