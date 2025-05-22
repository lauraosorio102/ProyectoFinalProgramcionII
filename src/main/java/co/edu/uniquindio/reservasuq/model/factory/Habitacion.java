package co.edu.uniquindio.reservasuq.model.factory;

import co.edu.uniquindio.reservasuq.model.entities.Cliente;
import co.edu.uniquindio.reservasuq.model.entities.Resenia;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
public class Habitacion extends Alojamiento implements Serializable {
    private Hotel hotel;

    public Habitacion(String nombre, String descripcion,  Image foto, int capacidadHuespedes,Hotel hotel, float precioPorNoche) {
        super(nombre, descripcion, null, foto, precioPorNoche ,capacidadHuespedes);
        this.hotel = hotel;
        this.setCiudad(hotel.getCiudad());
    }

    public float calcularPrecioTotal(int dias) {
        return this.getPrecioPorNoche() * dias;
    }

    public String toString() {
        return super.toString()+"Habitacion{" +
                "hotel=" + hotel.getNombre() +
                '}';
    }

    @Override
    public void agregarResenia(Cliente cliente, String titulo, String descripcion, int valoracion) {
        Resenia resenia = Resenia.builder().titulo(titulo).descripcion(descripcion).Valoracion(valoracion).nombreAlojamiento(super.getNombre()).nombreCliente(cliente.getNombre()).build();
        super.getResenias().add(resenia);
        hotel.actualizarHabitacion(this);
    }

    @Override
    public void eliminarResenia(Resenia resenia) {
        super.getResenias().remove(resenia);
        hotel.actualizarHabitacion(this);
    }

    @Override
    public void agregarServicio(String servicio) throws Exception {
        if (super.getServicios().contains(servicio)) {
            throw new Exception("El servicio ya existe");
        }
        super.getServicios().add(servicio);
        hotel.actualizarHabitacion(this);
    }

    @Override
    public void eliminarServicio(String servicio) throws Exception {
        if (!super.getServicios().contains(servicio)) {
            throw new Exception("Servicio no encontrado");
        }
        super.getServicios().remove(servicio);
        hotel.actualizarHabitacion(this);
    }
}
