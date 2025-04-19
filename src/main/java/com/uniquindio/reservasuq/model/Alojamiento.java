package com.uniquindio.reservasuq.model;

import java.util.List;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public abstract class Alojamiento {

    private String nombre;
    private String ciudad;
    private String descripcion;
    private String id;
    private String image;
    private double precioPorNoche;
    private int capacidadMaxima;
    private List<String> serviciosIncluidos;
    private List<Resenia> resenias;

}
