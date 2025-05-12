package co.edu.uniquindio.reservasuq.utils;

import java.util.Random;

public class GeneradorCodigos {

    public static String generarCodigo() {
        StringBuilder codigo = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            codigo.append(new Random().nextInt(9));
        }
        return codigo.toString();
    }
}
