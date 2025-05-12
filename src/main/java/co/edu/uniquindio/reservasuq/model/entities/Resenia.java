package co.edu.uniquindio.reservasuq.model.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Resenia {
    private String titulo, descripcion;
    private int Valoracion;
}
