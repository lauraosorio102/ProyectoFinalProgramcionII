package co.edu.uniquindio.reservasuq.controllers;
import co.edu.uniquindio.reservasuq.config.Sesion;
import co.edu.uniquindio.reservasuq.model.entities.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;


public class GestionarClienteController implements Initializable {

    private ControladorPrincipal controladorPrincipal;
    private Sesion sesion;
    private Cliente cliente;

    @FXML
    private Button buttonCambiargestionarCliente;

    @FXML
    private Button buttonRegistrargestionarCliente;

    @FXML
    private Button buttonRegistrargestionarCliente1;

    @FXML
    private PasswordField txtContraseñaGestionarCliente1;

    @FXML
    private TextField txtCorreoGestionarCliente1;

    @FXML
    private TextField txtNombreGestionarCliente;

    @FXML
    private PasswordField txtNuevacontraseñaGestionarCliente;

    @FXML
    private TextField txtTelefonoGestionarCliente;

    public GestionarClienteController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        sesion = Sesion.getInstance();
        cliente = (Cliente) sesion.getUsuario();
    }

    @FXML
    void CambiarAction(ActionEvent event) {
        try{
            controladorPrincipal.getEmpresaServicio().cambiarContrasenia(txtNuevacontraseñaGestionarCliente.getText(),sesion.getUsuario());
            txtNuevacontraseñaGestionarCliente.clear();
            controladorPrincipal.crearAlerta("Contraseña cambiada correctamente.", Alert.AlertType.INFORMATION);
        }catch (Exception e){
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void EditarAction(ActionEvent event) {
        try{
            controladorPrincipal.getEmpresaServicio().editarCliente(cliente, txtNombreGestionarCliente.getText(),txtTelefonoGestionarCliente.getText());
            txtNombreGestionarCliente.clear();
            txtTelefonoGestionarCliente.clear();
            controladorPrincipal.crearAlerta("Información editada correctamente.", Alert.AlertType.INFORMATION);
        }catch (Exception e){
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    void EliminarAction(ActionEvent event) {
        try{
            controladorPrincipal.getEmpresaServicio().eliminarCliente(cliente,txtCorreoGestionarCliente1.getText(),txtContraseñaGestionarCliente1.getText());
            sesion.cerrarSesion();
            controladorPrincipal.crearAlerta("Cuenta eliminada correctamente.", Alert.AlertType.INFORMATION);
            controladorPrincipal.abrirVentana("/co/edu/uniquindio/reservasuq/reservasUQView.fxml");
            controladorPrincipal.cerrarVentana(txtContraseñaGestionarCliente1);
        }catch (Exception e){
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
