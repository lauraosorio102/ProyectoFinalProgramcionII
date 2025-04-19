package com.uniquindio.reservasuq.model;

import java.time.LocalDate;
import java.util.UUID;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Resenia {

    private UUID codigo;
    private Cliente cliente;
    private String comentario;
    private int calificacion;
    private LocalDate fecha;
}
