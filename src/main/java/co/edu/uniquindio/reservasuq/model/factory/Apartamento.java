package co.edu.uniquindio.reservasuq.model.factory;

import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Apartamento extends Alojamiento{

    private float CostoAdicional;

    public Apartamento(String nombre, String descripcion, Ciudad ciudad, Image foto, float precioporNoche, int capacidadHuespedes, float costoAdicional) {
        super(nombre, descripcion, ciudad, foto, precioporNoche, capacidadHuespedes);
        this.CostoAdicional = costoAdicional;
    }
}
