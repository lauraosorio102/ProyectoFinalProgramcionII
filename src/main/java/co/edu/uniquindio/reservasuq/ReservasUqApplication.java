package co.edu.uniquindio.reservasuq;

import co.edu.uniquindio.reservasuq.controllers.ControladorPrincipal;
import co.edu.uniquindio.reservasuq.model.entities.Administrador;
import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import co.edu.uniquindio.reservasuq.model.entities.Cliente;
import co.edu.uniquindio.reservasuq.model.factory.Hotel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.time.LocalDate;

public class ReservasUqApplication extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(ReservasUqApplication.class.getResource("/co/edu/uniquindio/reservasuq/reservasUQView.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("BookYourStay");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}