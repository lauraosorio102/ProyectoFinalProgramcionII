package co.edu.uniquindio.reservasuq.repositories;

import co.edu.uniquindio.reservasuq.model.entities.Reserva;
import co.edu.uniquindio.reservasuq.utils.Constantes;
import co.edu.uniquindio.reservasuq.utils.Persistencia;

import java.io.IOException;
import java.util.ArrayList;

public class ReservaRepository {
    private ArrayList<Reserva> reservas;

    public ReservaRepository() {
        reservas = leerDatos();
    }

    public ArrayList<Reserva> listarReservas() {
        return reservas;
    }

    public void guardarDatos() {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_RESERVAS, reservas);
        } catch (IOException e) {
            System.err.println("Error guardando reservas: " + e.getMessage());
        }
    }

    public ArrayList<Reserva> leerDatos() {
        try {
            Object datos = Persistencia.deserializarObjeto(Constantes.RUTA_RESERVAS);
            if (datos != null) {
                return (ArrayList<Reserva>) datos;
            }
        } catch (Exception e) {
            System.err.println("Error cargando reservas: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
        guardarDatos();
    }

    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
        guardarDatos();
    }
}
