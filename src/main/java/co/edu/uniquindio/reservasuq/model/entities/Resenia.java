package co.edu.uniquindio.reservasuq.model.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class Resenia implements Serializable {
    private String titulo, descripcion,nombreAlojamiento,nombreCliente;
    private int Valoracion;

    public String toString() {
        return "Resenia{" +
                "titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", nombreAlojamiento='" + nombreAlojamiento + '\'' +
                ", nombreCliente='" + nombreCliente + '\'' +
                ", Valoracion=" + Valoracion +
                '}' + "\n";
    }
}
