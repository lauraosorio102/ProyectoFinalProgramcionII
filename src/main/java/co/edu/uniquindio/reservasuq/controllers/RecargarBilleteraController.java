package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.config.Sesion;
import co.edu.uniquindio.reservasuq.model.entities.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class RecargarBilleteraController implements Initializable {
    ControladorPrincipal controladorPrincipal;
    Sesion sesion;

    @FXML
    private Button buttonRecargarRecargarBilletera;

    @FXML
    private TextField txtCantidadRecargaBilletera;

    @FXML
    private Button buttonConsultarSaldo;

    public RecargarBilleteraController() {
        controladorPrincipal = ControladorPrincipal.getInstancia();
        sesion = Sesion.getInstance();
    }


    @FXML
    void ConsultarSaldoAction(ActionEvent event) {
        controladorPrincipal.crearAlerta("Su saldo es "+ controladorPrincipal.getEmpresaServicio().consultarSaldo((Cliente) sesion.getUsuario()), Alert.AlertType.INFORMATION);
    }

    @FXML
    void RecargarAction(ActionEvent event) {
        try{
            controladorPrincipal.getEmpresaServicio().recargarBilletera((Cliente)sesion.getUsuario(), txtCantidadRecargaBilletera.getText());
            txtCantidadRecargaBilletera.clear();
            controladorPrincipal.crearAlerta("Recarga exitosa.", Alert.AlertType.INFORMATION);
        }catch (Exception e){
            controladorPrincipal.crearAlerta(e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
