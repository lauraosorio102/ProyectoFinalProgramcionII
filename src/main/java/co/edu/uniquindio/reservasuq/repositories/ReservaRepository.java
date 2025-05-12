package co.edu.uniquindio.reservasuq.repositories;

import co.edu.uniquindio.reservasuq.model.entities.Reserva;

import java.util.ArrayList;

public class ReservaRepository {
    private ArrayList<Reserva> reservas;

    public ReservaRepository() {
        reservas = new ArrayList<>();
    }
}
