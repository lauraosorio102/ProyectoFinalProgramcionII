package co.edu.uniquindio.reservasuq.model.factory;

public class AlojamientoFactory {

    public static Class<?> seleccionarTipoAlojamiento(String alojamiento) {
        if (alojamiento != null) {
            return switch (alojamiento){
                case "Hotel" -> Hotel.class;
                case "Casa" -> Casa.class;
                case "Apartamento" -> Apartamento.class;
                case "Habitacion" -> Habitacion.class;
                default -> null;
            };
        }
        return null;
    }
}
