package support;

import definitions.hooks;
import io.cucumber.java.Scenario;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Excepciones {

    public static void excepcion(Runnable accion, WebDriver driver, String descripcion) {
        try {
            accion.run();
        } catch (ElementClickInterceptedException e) {
            manejarError(e, driver, descripcion, "No se pudo hacer click");
        } catch (Exception e) {
            manejarError(e, driver, descripcion, "Error inesperado");
        }
    }

    private static void manejarError(Exception e, WebDriver driver, String descripcion, String tipoError) {
        Scenario scenario = hooks.escenarioActual;

        String mensajeError = tipoError + " en: " + descripcion;
        String detalle = e.getMessage() != null ? e.getMessage().split("\n")[0] : "Sin detalles";

        if (scenario != null) {
            scenario.log(mensajeError);
            scenario.log("Detalle: " + detalle);
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", "Error_" + descripcion.replaceAll(" ", "_"));
            } catch (Exception screenshotError) {
                scenario.log("No se pudo capturar screenshot: " + screenshotError.getMessage());
            }
        } else {
            System.err.println(mensajeError);
            System.err.println("Detalle: " + detalle);
        }
         throw new RuntimeException(); //FL 2025-05-14
        //Assert.fail(mensajeError + "\n Detalle: " + detalle);
    }
}