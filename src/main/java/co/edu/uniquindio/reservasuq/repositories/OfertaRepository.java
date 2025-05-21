package co.edu.uniquindio.reservasuq.repositories;

import co.edu.uniquindio.reservasuq.model.entities.Oferta;
import co.edu.uniquindio.reservasuq.utils.Constantes;
import co.edu.uniquindio.reservasuq.utils.Persistencia;

import java.io.IOException;
import java.util.ArrayList;

public class OfertaRepository {
    private ArrayList<Oferta> ofertas;

    public OfertaRepository() {
        this.ofertas = leerDatos();
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
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_OFERTAS, ofertas);
        } catch (IOException e) {
            System.err.println("Error guardando ofertas: " + e.getMessage());
        }
    }

    public ArrayList<Oferta> leerDatos() {
        try {
            Object datos = Persistencia.deserializarObjeto(Constantes.RUTA_OFERTAS);
            if (datos != null) {
                return (ArrayList<Oferta>) datos;
            }
        } catch (Exception e) {
            System.err.println("Error cargando ofertas: " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public ArrayList<Oferta> listarOfertas() {
        return ofertas;
    }
}
