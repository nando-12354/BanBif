package definitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.E;
import io.cucumber.java.es.Y;
import pageobjects.LoginPage;

import static definitions.hooks.driver;

public class LoginDefinition {
    LoginPage login;

    public LoginDefinition() {
        login = new LoginPage();
    }

    @Dado("que la web este disponible")
    public void queLaWebEsteDisponible() {
        driver.get("https://rob.dev.banbif.com.pe/");
    }

    @Cuando("ingrese su correo corporativo {string}")
    public void ingreseSuCorreoCorporativo(String correo) {
        login.ingresarUserName(correo);
    }

    @Y("dar clic en el boton Siguiente")
    public void darClicEnElBotonSiguiente() {
        login.clicBotonSiguiente();
    }

    @Y("dar clic en el boton Inciar sesion")
    public void darClicEnElBotonInciarSesion() {
        login.clicBotonSiguiente();
        login.clicBotonSiguiente();
    }

    @E("ingrese la clave {string}")
    public void ingreseLaClave(String clave) {
        login.ingresarClave(clave);
    }
}
