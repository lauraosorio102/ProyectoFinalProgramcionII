package co.edu.uniquindio.reservasuq.model.factory;

import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Casa extends Alojamiento{

    private float CostoAdicional;
    private float precioporNoche;

    public Casa(String nombre, String descripcion, Ciudad ciudad, Image foto, float precioporNoche, int capacidadHuespedes, float costoAdicional){
        super(nombre, descripcion, ciudad, foto, capacidadHuespedes);
        this.CostoAdicional = costoAdicional;
        this.precioporNoche = precioporNoche;
    }

    public float calcularPrecioTotal(int dias) {
        return (precioporNoche * dias) + CostoAdicional;
    }
}
