package co.edu.uniquindio.reservasuq.services;

import co.edu.uniquindio.reservasuq.repositories.ReservaRepository;

public class ReservaServicio {
    private ReservaRepository reservaRepository;

    public ReservaServicio() {
        reservaRepository = new ReservaRepository();
    }
}
