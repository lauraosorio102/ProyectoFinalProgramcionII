package co.edu.uniquindio.reservasuq.model;

import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Casa extends Alojamiento{

    private float CostoAdicional;

    public Casa(String nombre, String descripcion, Ciudad ciudad, Image foto, float precioporNoche, int capacidadHuespedes,  float costoAdicional){
        super(nombre, descripcion, ciudad, foto, precioporNoche, capacidadHuespedes);
        this.CostoAdicional = costoAdicional;
    }
}
