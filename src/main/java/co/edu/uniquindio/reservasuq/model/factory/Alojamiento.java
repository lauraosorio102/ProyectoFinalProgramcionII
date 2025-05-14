package co.edu.uniquindio.reservasuq.model.factory;

import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import co.edu.uniquindio.reservasuq.model.entities.Resenia;
import co.edu.uniquindio.reservasuq.model.entities.Reserva;
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
    private int capacidadHuespedes;
    private float precioPorNoche;
    private ArrayList<String> servicios;
    private ArrayList<Resenia> resenias;

    public Alojamiento(String nombre,String descripcion,Ciudad ciudad,Image foto, float precioPorNoche  ,int capacidadHuespedes){
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        this.foto = foto;
        this.precioPorNoche = precioPorNoche;
        this.capacidadHuespedes = capacidadHuespedes;
        this.servicios = new ArrayList<>();
    }

    public abstract float calcularPrecioTotal(int dias);

    public void agregarResenia(String titulo, String descripcion, int valoracion) {
        Resenia resenia = Resenia.builder().titulo(titulo).descripcion(descripcion).Valoracion(valoracion).build();
        resenias.add(resenia);
    }

    public void eliminarResenia(Resenia resenia) {
        resenias.remove(resenia);
    }

    public void agregarServicio(String servicio) throws Exception {
        if (servicios.contains(servicio)) {
            throw new Exception("El servicio ya existe");
        }
        servicios.add(servicio);
    }

    public void eliminarServicio(String servicio) throws Exception {
        if (!servicios.contains(servicio)) {
            throw new Exception("Servicio no encontrado");
        }
        servicios.remove(servicio);
    }

    public ArrayList<String> listarServicios() {
        return servicios;
    }

}
