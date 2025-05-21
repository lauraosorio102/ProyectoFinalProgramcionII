package co.edu.uniquindio.reservasuq.services;

import co.edu.uniquindio.reservasuq.model.entities.Oferta;
import co.edu.uniquindio.reservasuq.repositories.OfertaRepository;

import java.time.LocalDate;
import java.util.ArrayList;

public class OfertaServicio {
    private final OfertaRepository ofertaRepository;

    public OfertaServicio() {
        ofertaRepository = new OfertaRepository();
    }

    public void agregarOferta(String nombre, LocalDate fechainicial, int diasOferta, int cantidadhuespedes, String valorDescuento, int diasReserva)throws Exception {
        StringBuilder e =  new StringBuilder();
        if (nombre.isEmpty()) e.append("El nombre no puede estar vacio - ");
        if (valorDescuento.isEmpty()) e.append("Descuento no puede estar vacio - ");
        if (!e.isEmpty())throw new Exception(e + "Verifique los datos y rellene");
        float descuento;
        try{
             descuento = Float.parseFloat(valorDescuento);
        }catch (NumberFormatException ex){
            throw new Exception("Valor de descuento invalido");
        }
        if (descuento <= 0.0f || descuento > 100.0f) throw new Exception("Valor de descuento no valido");
        ArrayList<LocalDate> fechas = new ArrayList<>();
        if (fechainicial != null && diasOferta > 0) {
            if (fechainicial.isBefore(LocalDate.now())) throw new Exception("Fecha inicial no valida");
            fechas = guardarFechas(fechainicial, diasOferta);
        }
        Oferta oferta = Oferta.builder().nombre(nombre).fechasdescuento(fechas).huespedes(cantidadhuespedes).valorDescuento(descuento).diasReserva(diasReserva).alojamientos(new ArrayList<>()).build();
        ofertaRepository.agregarOferta(oferta);
    }

    public ArrayList<LocalDate> guardarFechas(LocalDate fechaInicial,int dias){
        ArrayList<LocalDate> fechas = new ArrayList<>();
        for (int i = 0; i < dias; i++) {
            fechas.add(fechaInicial);
            fechaInicial = fechaInicial.plusDays(1);
        }
        return fechas;
    }


    public void eliminarOferta(Oferta oferta) throws Exception {
        if (oferta == null) throw new Exception("Seleccione una oferta para eliminarla");
        if (buscarOferta(oferta.getNombre()) == null) throw new Exception("No existe la oferta");
        ofertaRepository.eliminarOferta(oferta);
    }


    public Oferta buscarOferta(String nombre) {
        ArrayList<Oferta> ofertas = listarOfertas();
        for (Oferta oferta : ofertas) {
            if (oferta.getNombre().equals(nombre)) return oferta;
        }
        return null;
    }

    public ArrayList<Oferta> filtrarOferta(String nombre) {
        ArrayList<Oferta> ofertas = listarOfertas();
        ArrayList<Oferta> filtrados = new ArrayList<>();
        for (Oferta oferta : ofertas) {
            if (oferta.getNombre().toLowerCase().contains(nombre.toLowerCase())) filtrados.add(oferta);
        }
        return filtrados;
    }

    public ArrayList<Oferta> listarOfertas() {
        return ofertaRepository.listarOfertas();
    }

    public void editarOferta(Oferta oferta, String nombre, LocalDate fechainicial, int diasOferta, int cantidadhuespedes, String valorDescuento, int diasReserva) throws Exception {
        StringBuilder e =  new StringBuilder();
        if (oferta == null) e.append("Seleccione una oferta para editarla - ");
        if (nombre.isEmpty()) e.append("El nombre no puede estar vacio - ");
        if (valorDescuento.isEmpty()) e.append("Rellene el descuento - ");
        if (!e.isEmpty())throw new Exception(e + "Verifique los datos y rellene");
        float descuento;
        try{
            descuento = Float.parseFloat(valorDescuento);
        }catch (NumberFormatException ex){
            throw new Exception("Valor de descuento invalido");
        }
        if (descuento <= 0.0f || descuento > 100.0f) throw new Exception("Valor de descuento no valido");
        ArrayList<LocalDate> fechas = new ArrayList<>();
        if (fechainicial != null && diasOferta > 0) {
            if (fechainicial.isBefore(LocalDate.now())) throw new Exception("Fecha inicial no valida");
            fechas = guardarFechas(fechainicial, diasOferta);
        }
        oferta.setNombre(nombre);
        oferta.setFechasdescuento(fechas);
        oferta.setDiasReserva(diasReserva);
        oferta.setValorDescuento(descuento);
        oferta.setHuespedes(cantidadhuespedes);
        ofertaRepository.guardarDatos();
    }
}
