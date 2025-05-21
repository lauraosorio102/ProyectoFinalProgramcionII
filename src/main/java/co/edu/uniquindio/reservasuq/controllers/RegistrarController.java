package co.edu.uniquindio.reservasuq.controllers;

import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrarController implements Initializable {
    private final ControladorPrincipal controladorPrincipal;

    @FXML
    private Button buttonRegistrarRegistrar;

    @FXML
    private Button buttonVolverRegistrar;

    @FXML
    private TextField txtCedulaRegistrar;

    @FXML
    private PasswordField txtContraseñaRegistrar;

    @FXML
    private TextField txtCorreoRegistrar;

    @FXML
    private TextField txtNombreRegistrar;

    @FXML
    private TextField txtTelefonoRegistrar;

    public RegistrarController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }
    @FXML
    void RegistrarAction(ActionEvent event) {
        try{
            controladorPrincipal.getEmpresaServicio().agregarCliente(txtCorreoRegistrar.getText(),txtContraseñaRegistrar.getText(),txtCedulaRegistrar.getText(),txtNombreRegistrar.getText(),txtTelefonoRegistrar.getText());
            controladorPrincipal.abrirVentana("/co/edu/uniquindio/reservasuq/reservasUQView.fxml");
            controladorPrincipal.cerrarVentana(txtCedulaRegistrar);
        }catch(Exception e){
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void VolverAction(ActionEvent event) {
        controladorPrincipal.abrirVentana("/co/edu/uniquindio/reservasuq/reservasUQView.fxml");
        controladorPrincipal.cerrarVentana(txtCedulaRegistrar);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
