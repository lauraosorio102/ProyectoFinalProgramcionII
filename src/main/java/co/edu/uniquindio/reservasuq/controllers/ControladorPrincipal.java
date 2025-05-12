package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.services.EmpresaServicio;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;

public class ControladorPrincipal {
    private static ControladorPrincipal instancia;
    private EmpresaServicio empresaServicio;

    private ControladorPrincipal(){
        empresaServicio = new EmpresaServicio();
    }

    public static ControladorPrincipal getInstancia(){
        if (instancia == null){
            instancia = new ControladorPrincipal();
        }
        return instancia;
    }

    public void crearAlerta(String mensaje, Alert.AlertType tipo){
        Alert alert = new Alert(tipo);
        alert.setTitle("Alerta");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void cerrarVentana(Node node){
        Stage stage = (Stage) node.getScene().getWindow();
        stage.close();
    }

    public static String pedirCodigoPorInterfaz() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Codigo de verificacion");
        dialog.setHeaderText(null);
        dialog.setContentText("Ingresa el código que recibiste por correo:");

        Optional<String> result = dialog.showAndWait();
        return result.orElse("");
    }
}
