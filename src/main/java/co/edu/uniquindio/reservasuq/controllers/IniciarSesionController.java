package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.config.Sesion;
import co.edu.uniquindio.reservasuq.model.entities.Cliente;
import co.edu.uniquindio.reservasuq.model.entities.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class IniciarSesionController {
    private ControladorPrincipal controladorPrincipal;
    private Sesion sesion;

    @FXML
    private Button buttonIniciarIniciarSesion;

    @FXML
    private Button buttonVolverIniciarSesion;

    @FXML
    private PasswordField txtContraseñaIniciarSesion;

    @FXML
    private TextField txtCorreoIniciarSesion;

    public IniciarSesionController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        sesion = Sesion.getInstance();
    }
    @FXML
    void IniciarSesionAction(ActionEvent event) {
        try {
            Usuario usuario = controladorPrincipal.getEmpresaServicio().iniciarSesion(txtCorreoIniciarSesion.getText(),txtContraseñaIniciarSesion.getText());
            sesion.abrirSesion(usuario);
            if (usuario instanceof Cliente) {
                controladorPrincipal.abrirVentana("/co/edu/uniquindio/reservasuq/reservasUQView.fxml");
            }else{
                controladorPrincipal.abrirVentana("/co/edu/uniquindio/reservasuq/reservasUQAdminView.fxml");
            }
            controladorPrincipal.cerrarVentana(txtCorreoIniciarSesion);
        }catch (Exception e) {
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void VolverAction(ActionEvent event) {
        controladorPrincipal.abrirVentana("/co/edu/uniquindio/reservasuq/reservasUQView.fxml");
        controladorPrincipal.cerrarVentana(txtCorreoIniciarSesion);
    }

    @FXML
    void OlvideContraseñaAction(ActionEvent event) {
        controladorPrincipal.abrirVentana("/co/edu/uniquindio/reservasuq/olvideContraseniaView.fxml");
        controladorPrincipal.cerrarVentana(txtCorreoIniciarSesion);
    }
}
