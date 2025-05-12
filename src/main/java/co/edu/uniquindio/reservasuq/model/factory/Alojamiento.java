package co.edu.uniquindio.reservasuq.model.factory;

import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
public abstract class Alojamiento {
    private UUID id;
    private Ciudad ciudad;
    private String nombre, descripcion;
    private Image foto;
    private Float precioporNoche;
    private int capacidadHuespedes;
    private ArrayList<String> servicios;

    public Alojamiento(String nombre,String descripcion,Ciudad ciudad,Image foto,Float precioporNoche,int capacidadHuespedes){
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.foto = foto;
        this.precioporNoche = precioporNoche;
        this.capacidadHuespedes = capacidadHuespedes;
        this.servicios = new ArrayList<>();
    }
}
