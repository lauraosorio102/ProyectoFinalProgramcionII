package com.uniquindio.reservasuq.model;

import java.time.LocalDate;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Reserva {

    private String id;
    private Cliente cliente;
    private Alojamiento alojamiento;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private int nHuespedes;
    private Factura factura;
    private boolean canceleda;

}
