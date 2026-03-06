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

public class CampanaPage extends util {
    @FindBy(xpath = "(//button[@class='btn-show-nav'])[1]") protected WebElement btnSidebar;
    @FindBy(xpath = "//a[@href='/campaniaList']") protected WebElement btnCampana;
    @FindBy(xpath = "//span[text()='Campañas']") protected WebElement btnMenuCampana;
    @FindBy(xpath = "(//div/input)[1]") protected WebElement txtBuscarPorNombre;
    @FindBy(xpath = "//button[@class='btn-secundario']") protected WebElement btnBuscar;
    @FindBy(xpath = "//a[@title='Agregar']") protected WebElement btnAgregarCampana;
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
    public void clicAgregarCampana(){
        wait.until(ExpectedConditions.elementToBeClickable(btnAgregarCampana));
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
    public void ingresarBuscarPorNombre(String nombre){
        wait.until(ExpectedConditions.visibilityOf(txtBuscarPorNombre));
        txtBuscarPorNombre.sendKeys(nombre);
    }

}
