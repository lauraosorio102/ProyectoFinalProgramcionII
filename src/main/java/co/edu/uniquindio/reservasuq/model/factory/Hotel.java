package co.edu.uniquindio.reservasuq.model.factory;

import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import javafx.scene.image.Image;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter
public class Hotel extends Alojamiento implements Serializable {

    private ArrayList<Habitacion> habitaciones;

    public Hotel(String nombre, String descripcion, Ciudad ciudad, Image foto){
        super(nombre, descripcion, ciudad, foto, 0,0);
        this.habitaciones = new ArrayList<>();
    }

    public void agregarHabitacion(String numeroHabitacion, String descripcion, String costoHabitacion, int capacidadhuespedes, Image foto) throws Exception {
        StringBuilder e =  new StringBuilder();
        if (numeroHabitacion.isEmpty()) e.append("Escriba el número de habitación - ");
        if (descripcion.isEmpty())e.append("Escriba la descripcion - ");
        if (costoHabitacion.isEmpty()) e.append("Escriba el costo - ");
        if (capacidadhuespedes == 0) e.append("Escriba la capacidad - ");
        if (foto == null) e.append("Suba una foto - ");
        if (!e.isEmpty())throw new Exception(e+"Revise los campos y rellene.");
        if (buscarHabitacion(numeroHabitacion)!= null) throw new Exception("Ya existe una habitación con ese numero.");
        try{
            Float.parseFloat(costoHabitacion);
        }catch (Exception ex){
            throw new Exception("Costo de habitación invalido");
        }
        Habitacion habitacion = new Habitacion(numeroHabitacion,descripcion,foto,capacidadhuespedes,this,Float.parseFloat(costoHabitacion));
        habitaciones.add(habitacion);
        calcularCapacidad();
        calcularPrecioPorNoche();
    }

    public Habitacion buscarHabitacion(String numeroHabitacion){
        for (Habitacion habitacion : habitaciones) {
            if (habitacion.getNombre().equals(numeroHabitacion)) {
                return habitacion;
            }
        }
        return null;
    }

    public void eliminarHabitacion(Habitacion habitacion) throws Exception {
        if (habitacion == null) throw new Exception("Seleccione una habitación");
        if (!habitaciones.contains(habitacion)) throw new Exception("No se encuentra la habitacion");
        habitaciones.remove(habitacion);
        calcularCapacidad();
        calcularPrecioPorNoche();
    }

    public void actualizarHabitacion(Habitacion habitacion){
        for (Habitacion h : habitaciones) {
            if (h.getNombre().equals(habitacion.getNombre())) {
                h.setResenias(habitacion.getResenias());
                h.setServicios(habitacion.getServicios());
            }
        }
    }

    public void calcularCapacidad(){
        int capacidad = 0;
        for (Habitacion habitacion : habitaciones) {
            capacidad += habitacion.getCapacidadHuespedes();
        }
        setCapacidadHuespedes(capacidad);
    }

    public void calcularPrecioPorNoche(){
        float precio = 0;
        for (Habitacion habitacion : habitaciones) {
            precio += habitacion.getPrecioPorNoche();
        }
        setPrecioPorNoche(precio);
    }

    public float calcularPrecioTotal(int dias) {
        return this.getPrecioPorNoche()*dias;
    }

    public ArrayList<Habitacion> listarHabitaciones() {
        return habitaciones;
    }

    public String toString() {
        return super.toString()+"Hotel{" +
                "habitaciones=" + habitaciones +
                '}';
    }
}
