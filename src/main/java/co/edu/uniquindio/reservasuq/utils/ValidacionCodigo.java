package co.edu.uniquindio.reservasuq.utils;

import javafx.scene.control.TextInputDialog;

import java.util.Optional;
import java.util.Random;

public class ValidacionCodigo {

    public static String generarCodigo() {
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            codigo.append(new Random().nextInt(9));
        }
        return codigo.toString();
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
