package co.edu.uniquindio.reservasuq.model.factory;

import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Habitacion extends Alojamiento{
    private Hotel hotel;

    public Habitacion(String nombre, String descripcion,  Image foto, int capacidadHuespedes,Hotel hotel, float precioPorNoche) {
        super(nombre, descripcion, null, foto, precioPorNoche ,capacidadHuespedes);
        this.hotel = hotel;
        this.setCiudad(hotel.getCiudad());
    }


    public float calcularPrecioTotal(int dias) {
        return this.getPrecioPorNoche() * dias;
    }
}
