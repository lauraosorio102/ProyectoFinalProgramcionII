package co.edu.uniquindio.reservasuq.controllers;

import co.edu.uniquindio.reservasuq.services.EmpresaServicio;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import lombok.Getter;

import java.io.IOException;
import java.util.Optional;

public class ControladorPrincipal {
    private static ControladorPrincipal instancia;
    @Getter
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

    public void abrirVentana(String rutaFXML) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(rutaFXML));
            Parent root = loader.load();

            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("BookYourStay");
            stage.setScene(scene);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
