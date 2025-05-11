package co.edu.uniquindio.reservasuq.model;

import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Hotel extends Alojamiento{

    private ArrayList<Habitacion> habitaciones;

    public Hotel(String nombre, String descripcion, Ciudad ciudad, Image foto, float precioporNoche, int capacidadHuespedes){
        super(nombre, descripcion, ciudad, foto, precioporNoche, capacidadHuespedes);
        this.habitaciones = new ArrayList<>();
    }
}
