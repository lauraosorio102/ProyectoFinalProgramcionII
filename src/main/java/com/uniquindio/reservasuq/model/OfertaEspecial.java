package com.uniquindio.reservasuq.model;

import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class OfertaEspecial {

    private String id;
    private Alojamiento alojamiento;
    private String descripcion;
    private double descuento;
    private LocalDate fechaIncio;
    private LocalDate fechaFin;
}
