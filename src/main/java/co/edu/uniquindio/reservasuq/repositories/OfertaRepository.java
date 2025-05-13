package co.edu.uniquindio.reservasuq.repositories;

import co.edu.uniquindio.reservasuq.model.entities.Oferta;

import java.util.ArrayList;

public class OfertaRepository {
    private ArrayList<Oferta> ofertas;

    public OfertaRepository() {
        ofertas = new ArrayList<>();
    }

    public void agregarOferta(Oferta oferta) {
        ofertas.add(oferta);
        guardarDatos();
    }


    public void eliminarOferta(Oferta oferta) {
        ofertas.remove(oferta);
        guardarDatos();
    }

    public void guardarDatos() {
    }

    public ArrayList<Oferta> listarOfertas() {
        return ofertas;
    }
}
