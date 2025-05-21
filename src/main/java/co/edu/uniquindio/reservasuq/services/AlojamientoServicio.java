package co.edu.uniquindio.reservasuq.services;

import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import co.edu.uniquindio.reservasuq.model.factory.*;
import co.edu.uniquindio.reservasuq.repositories.AlojamientoRepository;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class AlojamientoServicio {
    private final AlojamientoRepository alojamientoRepository;

    public AlojamientoServicio() {
        alojamientoRepository = new AlojamientoRepository();
    }

    public void agregarCasa(String nombre, String descripcion, Ciudad ciudad, Image foto, String precioporNoche, int capacidadHuespedes, String costoAdicional) throws Exception {
        StringBuilder e = verificarDatos(nombre,descripcion,ciudad,foto,precioporNoche,capacidadHuespedes,costoAdicional);
        if (!e.isEmpty()) throw new Exception(e+"Verifique los datos y rellene.");
        float precioNoche = 0;
        float costoAdicionall = 0;
        try{
            precioNoche = Float.parseFloat(precioporNoche);
        }catch (NumberFormatException ex){
            e.append("Precio por noche no valido - ");
        }
        try{
            costoAdicionall = Float.parseFloat(costoAdicional);
        }catch (NumberFormatException ex){
            e.append("Costo adicional no valido - ");
        }
        if (!e.isEmpty()) throw new Exception(e+"Verifique los datos");
        if (precioNoche <= 0 )e.append("Precio no valido - ");
        if (costoAdicionall <= 0)e.append("Cantidad de huespedes no valido - ");
        if (!e.isEmpty()) throw new Exception(e+"Verifique los datos");
        if (buscarAlojamiento(nombre) != null && buscarAlojamiento(nombre).getCiudad().equals(ciudad)) throw new Exception(nombre+" ya existe en esa ciudad");
        Casa casa = new Casa(nombre,descripcion,ciudad,foto,precioNoche,capacidadHuespedes,costoAdicionall);
        alojamientoRepository.agregarAlojamiento(casa);
    }

    public void agregarApartamento(String nombre, String descripcion, Ciudad ciudad, Image foto, String precioporNoche, int capacidadHuespedes, String costoAdicional) throws Exception {
        StringBuilder e = verificarDatos(nombre,descripcion,ciudad,foto,precioporNoche,capacidadHuespedes,costoAdicional);
        if (!e.isEmpty()) throw new Exception(e+"Verifique los datos y rellene.");
        float precioNoche = 0;
        float Costoadicional = 0;
        try{
            precioNoche = Float.parseFloat(precioporNoche);
        }catch (NumberFormatException ex){
            e.append("Precio por noche no valido - ");
        }
        try{
            Costoadicional = Float.parseFloat(costoAdicional);
        }catch (NumberFormatException ex){
            e.append("Costo adicional no valido - ");
        }
        if (!e.isEmpty()) throw new Exception(e+"Verifique los datos");
        if (precioNoche <= 0 )e.append("Precio no valido - ");
        if (Costoadicional <= 0)e.append("Cantidad de huespedes no valido - ");
        if (!e.isEmpty()) throw new Exception(e+"Verifique los datos");
        if (buscarAlojamiento(nombre) != null && buscarAlojamiento(nombre).getCiudad().equals(ciudad)) throw new Exception(nombre+" ya existe en esa ciudad");
        Apartamento apartamento = new Apartamento(nombre,descripcion,ciudad,foto,precioNoche,capacidadHuespedes,Costoadicional);
        alojamientoRepository.agregarAlojamiento(apartamento);
    }

    public StringBuilder verificarDatos(String nombre, String descripcion, Ciudad ciudad, Image foto)  {
        StringBuilder e = new StringBuilder();
        if (nombre.isEmpty()) e.append("El nombre no puede ser vacio - ");
        if (descripcion.isEmpty()) e.append("La descripcion no puede ser vacia - ");
        if (ciudad == null) e.append("La ciudad no puede ser vacia - ");
        if (foto == null) e.append("Seleccione una foto - ");
        return e;
    }

    public StringBuilder verificarDatos(String nombre, String descripcion, Ciudad ciudad, Image foto, String precioporNoche, int capacidadHuespedes, String costoAdicional){
        StringBuilder e = verificarDatos(nombre,descripcion,ciudad,foto);
        if (precioporNoche.isEmpty()) e.append("El precio por noche no puede estar vacio - ");
        if (capacidadHuespedes == 0) e.append("Debe seleccionar una capacidad válida de capacidad huespedes - ");
        if (costoAdicional.isEmpty()) e.append("El costo adicional no puede estar vacio - ");
        return e;
    }


    public void agregarHotel(String nombre, String descripcion, Ciudad ciudad, Image foto) throws Exception {
        StringBuilder e = verificarDatos(nombre,descripcion,ciudad,foto);
        if (!e.isEmpty()) throw new Exception(e+ "Verifique los datos y rellene.");
        Hotel hotel = new Hotel(nombre,descripcion,ciudad,foto);
        if (buscarAlojamiento(nombre) != null && buscarAlojamiento(nombre).getCiudad().equals(ciudad)) throw new Exception(nombre+" ya existe en esa ciudad");
        alojamientoRepository.agregarAlojamiento(hotel);
    }


    public void guardarDatos() {
        alojamientoRepository.guardarDatos();
    }

    public ArrayList<Alojamiento> listarAlojamientos() {
        return alojamientoRepository.listarAlojamientos();
    }

    public Alojamiento buscarAlojamiento(String nombre) {
        ArrayList<Alojamiento> alojamientos = listarAlojamientos();
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento.getNombre().equals(nombre)) return alojamiento;
        }
        return null;
    }

    public void eliminarAlojamiento(Alojamiento alojamiento) throws Exception {
        if (alojamiento == null) throw new Exception("Seleccione un alojamiento.");
        if (buscarAlojamiento(alojamiento.getNombre()) == null) throw new Exception("No existe el alojamiento");
        alojamientoRepository.eliminarAlojamiento(alojamiento);
    }

    public ArrayList<Alojamiento> filtrarAlojamientos(Class<?> tipoAlojamiento, Ciudad ciudad, int capacidadHuespedes,String nombre) {
        ArrayList<Alojamiento> alojamientos = alojamientoRepository.listarAlojamientos();
        ArrayList<Alojamiento> filtrados = new ArrayList<>();

        for (Alojamiento alojamiento : alojamientos) {
            boolean cumple = true;

            if (tipoAlojamiento != null && !alojamiento.getClass().equals(tipoAlojamiento)) {
                cumple = false;
            }
            if (ciudad != null && !alojamiento.getCiudad().equals(ciudad)) {
                cumple = false;
            }
            if (capacidadHuespedes > 0 && alojamiento.getCapacidadHuespedes() < capacidadHuespedes) {
                cumple = false;
            }
            if (!alojamiento.getNombre().toLowerCase().contains(nombre.toLowerCase())) {
                cumple = false;
            }
            if (cumple) {
                filtrados.add(alojamiento);
            }
        }
        return filtrados;
    }

    public ArrayList<Alojamiento> listarAlojamientosPrincipales() {
        ArrayList<Alojamiento> alojamientos = alojamientoRepository.listarAlojamientos();
        ArrayList<Alojamiento> filtrados = new ArrayList<>();
        for (Alojamiento alojamiento : alojamientos) {
            if (!(alojamiento instanceof Habitacion)) filtrados.add(alojamiento);
        }
        return filtrados;
    }

    public void editarAlojamiento(Alojamiento alojamiento,String nombre, String descripcion, Ciudad ciudad, Image foto, String precioporNoche, int capacidadHuespedes) throws Exception {
        StringBuilder e = verificarDatos(nombre, descripcion, ciudad, foto);
        if (precioporNoche.isEmpty()) e.append("El precio por noche no puede estar vacio - ");
        if (capacidadHuespedes == 0) e.append("Debe seleccionar una capacidad válida de capacidad huespedes - ");
        if (!e.isEmpty())throw new Exception("Verifique los datos y rellene - ");
        if (buscarAlojamiento(nombre) != null && buscarAlojamiento(nombre).getCiudad().equals(ciudad)) throw new Exception(nombre+" ya existe en esa ciudad");
        if (!alojamiento.getClass().equals(Habitacion.class)) {
            float precioNoche = 0;
            try{
                precioNoche = Float.parseFloat(precioporNoche);
            }catch (NumberFormatException ex){
                e.append("Precio por noche no valido - ");
            }
            if (!e.isEmpty()) throw new Exception(e+"Verifique los datos");
            if (precioNoche <= 0 )e.append("Precio no valido - ");
            alojamiento.setPrecioPorNoche(precioNoche);
        }
        alojamiento.setNombre(nombre);
        alojamiento.setDescripcion(descripcion);
        alojamiento.setCiudad(ciudad);
        alojamiento.setFoto(foto);
        alojamiento.setCapacidadHuespedes(capacidadHuespedes);
        alojamientoRepository.guardarDatos();
    }

    public ArrayList<Hotel> listarHoteles() {
        ArrayList<Alojamiento> alojamientos = alojamientoRepository.listarAlojamientos();
        ArrayList<Hotel> filtrados = new ArrayList<>();
        for (Alojamiento alojamiento : alojamientos) {
            if (alojamiento instanceof Hotel) filtrados.add((Hotel) alojamiento);
        }
        return filtrados;
    }

    public void guardarHabitacion(Habitacion habitacion) {
        alojamientoRepository.agregarAlojamiento(habitacion);
    }

    public void eliminarHabitacion(Habitacion habitacion) {
        alojamientoRepository.eliminarAlojamiento(habitacion);
    }
}
