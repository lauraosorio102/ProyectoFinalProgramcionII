package co.edu.uniquindio.reservasuq.model.entities;

import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

@Builder
@Getter
@Setter
public class Oferta implements Serializable {
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
            if (fecha.isBefore(inicioDescuento) && fecha.isAfter(finDescuento)) {
                cumple = false;
                break;
            }
        }
        if (cumple) return precio - precio*valorDescuento/100;
        return precio;
    }

    public void agregarAlojamiento(Alojamiento alojamiento) throws Exception {
        if (alojamiento == null) throw new Exception("Debe seleccionar un alojamiento.");
        if (!alojamientos.contains(alojamiento)){
            alojamientos.add(alojamiento);
        }else{
            throw new Exception("Esta oferta ya tiene ese alojamiento.");
        }
    }

    public void eliminarAlojamiento(Alojamiento alojamiento) throws Exception {
        if (alojamiento == null) throw new Exception("Debe seleccionar un alojamiento.");
        if (alojamientos.contains(alojamiento)){
            alojamientos.remove(alojamiento);
        }else{
            throw new Exception("Esta oferta no tiene ese alojamiento.");
        }
    }
}
