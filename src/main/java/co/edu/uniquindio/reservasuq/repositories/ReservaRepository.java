package co.edu.uniquindio.reservasuq.repositories;

import co.edu.uniquindio.reservasuq.model.entities.Reserva;

import java.util.ArrayList;

public class ReservaRepository {
    private ArrayList<Reserva> reservas;

    public ReservaRepository() {
        reservas = new ArrayList<>();
    }

    public ArrayList<Reserva> listarReservas() {
        return reservas;
    }

    public void guardarDatos(){

    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }

    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }
}
