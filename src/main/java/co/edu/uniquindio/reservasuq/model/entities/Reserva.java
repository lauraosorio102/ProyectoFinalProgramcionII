package co.edu.uniquindio.reservasuq.model.entities;

import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

@Builder
@Getter
@Setter
public class Reserva implements Serializable {
    private UUID id;
    private Alojamiento alojamiento;
    private Cliente cliente;
    private int numeroHuespedes;
    private ArrayList<LocalDate> diasReserva;
    private float Precio;
    private Factura factura;

    @Override
    public String toString() {
        return "Reserva{" +
                "alojamiento=" + alojamiento +
                ", cliente=" + cliente +
                ", numeroHuespedes=" + numeroHuespedes +
                ", diasReserva=" + diasReserva +
                ", Precio=" + Precio +
                '}';
    }
}
