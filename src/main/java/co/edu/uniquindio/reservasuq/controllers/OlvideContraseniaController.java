package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.model.entities.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;

public class OlvideContraseniaController implements Initializable {

    private ControladorPrincipal controladorPrincipal;
    private Usuario usuarioCorreo;

    @FXML
    private Label LabelContraseña;

    @FXML
    private Label LabelCorreo;

    @FXML
    private Button btnCambiarContraseña;

    @FXML
    private Button buttonVerificarCorreo;

    @FXML
    private Button buttonVolverIniciarSesion;

    @FXML
    private PasswordField txtContraseña;

    @FXML
    private TextField txtCorreoIniciar;

    public OlvideContraseniaController(){
        controladorPrincipal = ControladorPrincipal.getInstancia();
    }
    @FXML
    void CambiarContraseñaAction(ActionEvent event) {
        try{
            controladorPrincipal.getEmpresaServicio().cambiarContrasenia(txtContraseña.getText(), usuarioCorreo);
            controladorPrincipal.abrirVentana("/co/edu/uniquindio/reservasuq/iniciarSesionView.fxml");
            controladorPrincipal.cerrarVentana(buttonVolverIniciarSesion);
        }catch (Exception e){
            controladorPrincipal.crearAlerta(e.getMessage(),Alert.AlertType.ERROR);
        }
    }

    @FXML
    void VolverAction(ActionEvent event) {
        controladorPrincipal.abrirVentana("/co/edu/uniquindio/reservasuq/iniciarSesionView.fxml");
        controladorPrincipal.cerrarVentana(buttonVolverIniciarSesion);
    }

    @FXML
    void VerificarCorreoAction(ActionEvent event) {
        Usuario usuario = controladorPrincipal.getEmpresaServicio().buscarUsuario(txtCorreoIniciar.getText());
        if (usuario != null) {
            LabelContraseña.setVisible(true);
            txtContraseña.setVisible(true);
            btnCambiarContraseña.setVisible(true);
            LabelCorreo.setVisible(false);
            txtCorreoIniciar.setVisible(false);
            buttonVerificarCorreo.setVisible(false);
            usuarioCorreo = usuario;
        }else{
            controladorPrincipal.crearAlerta("No se encontró ningún usuario.", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        LabelContraseña.setVisible(false);
        txtContraseña.setVisible(false);
        btnCambiarContraseña.setVisible(false);
    }
}
