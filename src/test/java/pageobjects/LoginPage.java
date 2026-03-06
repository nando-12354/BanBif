package pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import support.util;

public class LoginPage extends util {
    @FindBy (name = "loginfmt") protected WebElement txtUsername;
    @FindBy (id = "idSIButton9") protected WebElement btnSiguiente;
    @FindBy (name = "passwd") protected WebElement txtClave;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }
    public void ingresarUserName(String user){
        wait.until(ExpectedConditions.visibilityOf(txtUsername));
        txtUsername.sendKeys(user);
    }
    public void clicBotonSiguiente(){
        wait.until(ExpectedConditions.elementToBeClickable(btnSiguiente));
        btnSiguiente.click();
    }
    public void ingresarClave(String clave){
        wait.until(ExpectedConditions.visibilityOf(txtClave));
        txtClave.sendKeys(clave);
    }
}
