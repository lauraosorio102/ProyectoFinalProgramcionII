package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.config.Sesion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

import java.net.URL;
import java.util.ResourceBundle;

public class CambiarContraseniaController implements Initializable {

    private final ControladorPrincipal controladorPrincipal;
    private final Sesion sesion;
    @FXML
    private Button buttonCambiargestionarCliente;

    @FXML
    private PasswordField txtNuevacontraseñaGestionarCliente;

    public CambiarContraseniaController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        sesion = Sesion.getInstance();
    }

    @FXML
    void CambiarAction(ActionEvent event) {
        try{
            controladorPrincipal.getEmpresaServicio().cambiarContrasenia(txtNuevacontraseñaGestionarCliente.getText(),sesion.getUsuario());
            txtNuevacontraseñaGestionarCliente.clear();
            controladorPrincipal.crearAlerta("Contraseña cambiada correctamente",Alert.AlertType.INFORMATION);
        }catch(Exception e){
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
