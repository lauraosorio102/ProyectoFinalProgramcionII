package co.edu.uniquindio.reservasuq.repositories;

import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import co.edu.uniquindio.reservasuq.model.factory.Casa;

import java.util.ArrayList;

public class AlojamientoRepository {
    private ArrayList<Alojamiento> alojamientos;

    public AlojamientoRepository() {
        alojamientos = new ArrayList<>();
    }

    public void agregarAlojamiento(Alojamiento alojamiento) {
        alojamientos.add(alojamiento);
        guardarDatos();
    }


    public void guardarDatos() {
    }

    public ArrayList<Alojamiento> listarAlojamientos() {
        return alojamientos;
    }

    public void eliminarAlojamiento(Alojamiento alojamiento) {
        alojamientos.remove(alojamiento);
        guardarDatos();
    }
}
