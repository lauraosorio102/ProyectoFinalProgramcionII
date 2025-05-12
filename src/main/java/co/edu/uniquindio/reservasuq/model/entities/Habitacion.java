package co.edu.uniquindio.reservasuq.model.entities;

import javafx.scene.image.Image;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Habitacion {
    private String numeroHabitacion, descripcion;
    private float costoHabitacion;
    private int capacidadHuespedes;
    private Image fotoHabitacion;
}
