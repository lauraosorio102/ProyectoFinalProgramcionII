package co.edu.uniquindio.reservasuq.services;

import co.edu.uniquindio.reservasuq.repositories.OfertaRepository;

public class OfertaServicio {
    private OfertaRepository ofertaRepository;

    public OfertaServicio() {
        ofertaRepository = new OfertaRepository();
    }
}
