package co.edu.uniquindio.reservasuq.model.factory;

import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Apartamento extends Alojamiento implements Serializable {

    private float CostoAdicional;

    public Apartamento(String nombre, String descripcion, Ciudad ciudad, Image foto, float precioporNoche, int capacidadHuespedes, float costoAdicional) {
        super(nombre, descripcion, ciudad, foto,precioporNoche, capacidadHuespedes);
        this.CostoAdicional = costoAdicional;
    }

    public float calcularPrecioTotal(int dias) {
        return (this.getPrecioPorNoche() * dias) + CostoAdicional;
    }

    public String toString() {
        return super.toString()+"Apartamento{" +
                "CostoAdicional=" + CostoAdicional +
                '}';
    }
}
