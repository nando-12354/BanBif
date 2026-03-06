package definitions;

import io.cucumber.java.PendingException;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import pageobjects.CampanaPage;
import io.cucumber.datatable.DataTable;
import java.util.List;

public class CampanaDefinition {
    CampanaPage campana;
    String nombreBusquado;
    public CampanaDefinition() {
    campana = new CampanaPage();
    }

    @Entonces("El usuario selecciona el modulo Campanas opcion Campana del sidebar")
    public void elUsuarioSeleccionaElModuloCampanasOpcionCampanaDelSidebar() {
        campana.clicBotonSidebar();
        campana.clicBotonMenuCampana();
        campana.clicBotonCampana();
    }

    @Y("Se muestra una tabla con el listado de campañas con los datos")
    public void seMuestraUnaTablaConElListadoDeCampañasConLosDatos(DataTable campos) {
        List<String> opciones = campos.asList();
        for (String opcion : opciones) {
            campana.validarCampos(opcion);
        }
    }

    @Y("Arriba de la tabla aparecen las siguientes opciones")
    public void arribaDeLaTablaAparecenLasSiguientesOpciones(DataTable campos) {
        List<String> opciones = campos.asList();
        for (String opcion : opciones) {
            campana.validarOpciones(opcion);
        }
    }

    @Y("el usuario ingresa un valor en el campo de busqueda {string}")
    public void elUsuarioIngresaUnValorEnElCampoDeBusqueda(String nombre) {
        nombreBusquado=nombre;
        campana.ingresarBuscarPorNombre(nombre);
    }

    @Y("selecciona el boton Buscar")
    public void seleccionaElBotonBuscar() {
        campana.clicBotonBuscar();
    }

    @Y("se muestran las campanas cuyos nombres coincidan con el valor ingresado")
    public void seMuestranLasCampanasCuyosNombresCoincidanConElValorIngresado() {
        campana.validarCampos(nombreBusquado);
        hooks.escenarioActual.log("Nombre: " + nombreBusquado);
    }

    @Y("el usuario selecciona el boton Agregar campana")
    public void elUsuarioSeleccionaElBotonAgregarCampana() {
        campana.clicAgregarCampana();
    }

    @Y("Se muestra un modal con un formulario con los siguientes campos obligatorios")
    public void seMuestraUnModalConUnFormularioConLosSiguientesCamposObligatorios(DataTable campos) {
        List<String> opciones = campos.asList();
        for (String opcion : opciones) {
            campana.validarOpciones(opcion);
        }
    }
}
