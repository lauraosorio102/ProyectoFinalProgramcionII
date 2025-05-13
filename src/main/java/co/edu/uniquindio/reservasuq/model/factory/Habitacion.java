package co.edu.uniquindio.reservasuq.model.factory;

import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Habitacion extends Alojamiento{
    private float costoHabitacion;
    private Hotel hotel;

    public Habitacion(String nombre, String descripcion,  Image foto, int capacidadHuespedes,Hotel hotel, float costoHabitacion) {
        super(nombre, descripcion, null, foto, capacidadHuespedes);
        this.hotel = hotel;
        this.setCiudad(hotel.getCiudad());
        this.costoHabitacion = costoHabitacion;
    }


    public float calcularPrecioTotal(int dias) {
        return costoHabitacion * dias;
    }
}
