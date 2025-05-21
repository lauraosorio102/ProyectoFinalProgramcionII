package co.edu.uniquindio.reservasuq.repositories;

import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import co.edu.uniquindio.reservasuq.model.factory.Casa;
import co.edu.uniquindio.reservasuq.utils.Constantes;
import co.edu.uniquindio.reservasuq.utils.Persistencia;

import java.io.IOException;
import java.util.ArrayList;

public class AlojamientoRepository {
    private ArrayList<Alojamiento> alojamientos;

    public AlojamientoRepository() {
        this.alojamientos = leerDatos();
    }

    public void agregarAlojamiento(Alojamiento alojamiento) {
        alojamientos.add(alojamiento);
        guardarDatos();
    }


    public void guardarDatos() {
        try{
            Persistencia.serializarObjeto(Constantes.RUTA_ALOJAMIENTOS,alojamientos);
        } catch (IOException e) {
            System.err.println("Error guardando alojamientos: " + e.getMessage());
        }
    }

    public ArrayList<Alojamiento> leerDatos(){
        try {
            Object datos = Persistencia.deserializarObjeto(Constantes.RUTA_ALOJAMIENTOS);
            if(datos != null){
                return (ArrayList<Alojamiento>) datos;
            }
        } catch (Exception e) {
            System.err.println("Error cargando pacientes: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public ArrayList<Alojamiento> listarAlojamientos() {
        return alojamientos;
    }

    public void eliminarAlojamiento(Alojamiento alojamiento) {
        alojamientos.remove(alojamiento);
        guardarDatos();
    }


}
