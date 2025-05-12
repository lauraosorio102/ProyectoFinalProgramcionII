package co.edu.uniquindio.reservasuq.model.factory;

public class AlojamientoFactory {

    public static Class<?> seleccionarAlojamiento(String alojamiento) {
        return switch (alojamiento){
            case "Hotel" -> Hotel.class;
            case "Casa" -> Casa.class;
            case "Aparatamento" -> Apartamento.class;
            default -> null;
        };
    }
}
