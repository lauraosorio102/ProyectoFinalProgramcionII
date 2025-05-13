package co.edu.uniquindio.reservasuq.model.entities;

import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;

@Builder
@Getter
@Setter
public class Oferta {
    private String nombre;
    private float valorDescuento;

    private ArrayList<LocalDate> fechasdescuento;
    private int huespedes;
    private int diasReserva;
    private ArrayList<Alojamiento> alojamientos;

    public float verificarDescuento(Alojamiento alojamiento,float precio, int cantidadhuespedes, int dias, ArrayList<LocalDate>fechas){
        boolean cumple = true;
        if (!alojamientos.contains(alojamiento)) cumple = false;
        if (huespedes > 0 && cantidadhuespedes < huespedes) cumple = false;
        if (diasReserva>0 && dias < diasReserva) cumple = false;
        LocalDate inicioDescuento = fechasdescuento.getFirst();
        LocalDate finDescuento = fechasdescuento.getLast();
        for (LocalDate fecha : fechas) {
            if (!fecha.isBefore(inicioDescuento) && !fecha.isAfter(finDescuento)) {
                cumple = false;
            }
        }
        if (cumple) return precio - precio*valorDescuento/100;
        return precio;
    }
}
