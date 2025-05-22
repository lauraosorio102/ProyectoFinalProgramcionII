package co.edu.uniquindio.reservasuq.model.factory;

import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import co.edu.uniquindio.reservasuq.model.entities.Cliente;
import co.edu.uniquindio.reservasuq.model.entities.Resenia;
import co.edu.uniquindio.reservasuq.model.entities.Reserva;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;
import javafx.embed.swing.SwingFXUtils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
public abstract class Alojamiento implements Serializable {
    private UUID id;
    private Ciudad ciudad;
    private String nombre, descripcion;

    private transient  Image foto;
    private byte[] fotoBytes;

    private int capacidadHuespedes;
    private float precioPorNoche;
    private ArrayList<String> servicios;
    private ArrayList<Resenia> resenias;

    public Alojamiento(String nombre,String descripcion,Ciudad ciudad,Image foto, float precioPorNoche  ,int capacidadHuespedes){
        this.id = UUID.randomUUID();
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ciudad = ciudad;
        setFoto(foto);
        this.precioPorNoche = precioPorNoche;
        this.capacidadHuespedes = capacidadHuespedes;
        this.servicios = new ArrayList<>();
        this.resenias = new ArrayList<>();
    }

    public void setFoto(Image foto){
        this.foto = foto;
        try {
            if (foto != null) {
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                BufferedImage bufferedImage = SwingFXUtils.fromFXImage(foto, null);
                ImageIO.write(bufferedImage, "png", bos);
                this.fotoBytes = bos.toByteArray();
            }
        } catch (Exception e) {
            System.err.println("No se pudo convertir imagen a byte[]: " + e.getMessage());
            this.fotoBytes = null;
        }
    }

    public Image getFoto() {
        if (foto == null && fotoBytes != null) {
            try (ByteArrayInputStream bis = new ByteArrayInputStream(fotoBytes)) {
                foto = new Image(bis);
            } catch (Exception e) {
                System.err.println("Error reconstruyendo imagen desde bytes");
            }
        }
        return foto;
    }

    public abstract float calcularPrecioTotal(int dias);

    public void agregarResenia(Cliente cliente, String titulo, String descripcion, int valoracion) {
        Resenia resenia = Resenia.builder().titulo(titulo).descripcion(descripcion).Valoracion(valoracion).nombreAlojamiento(this.nombre).nombreCliente(cliente.getNombre()).build();
        resenias.add(resenia);
    }

    public void eliminarResenia(Resenia resenia) {
        resenias.remove(resenia);
    }

    public ArrayList<Resenia> listarresenias() {
        return resenias;
    }

    public void agregarServicio(String servicio) throws Exception {
        if (servicio.isEmpty()) throw new Exception("Rellena el servicio");
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

    @Override
    public String toString() {
        return "Alojamiento{" +
                "nombre='" + nombre + '\'' +
                ", ciudad=" + ciudad +
                ", descripcion='" + descripcion + '\'' +
                ", precioPorNoche=" + precioPorNoche +
                '}';
    }
}
