package co.edu.uniquindio.reservasuq.model;

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
    private ArrayList<LocalDate> fechas;
    private int huespedes;
    private float valorDescuento;
}
