package com.uniquindio.reservasuq.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Habitacion {

    private String numero;
    private double precio;
    private int capacidad;
    private String image;
    private String descripcion;
}
