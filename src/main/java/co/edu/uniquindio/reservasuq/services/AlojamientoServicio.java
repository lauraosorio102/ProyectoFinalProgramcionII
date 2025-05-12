package co.edu.uniquindio.reservasuq.services;

import co.edu.uniquindio.reservasuq.repositories.AlojamientoRepository;

public class AlojamientoServicio {
    private AlojamientoRepository alojamientoRepository;

    public AlojamientoServicio() {
        alojamientoRepository = new AlojamientoRepository();
    }
}
