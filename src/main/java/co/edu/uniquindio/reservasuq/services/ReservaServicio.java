package co.edu.uniquindio.reservasuq.services;

import co.edu.uniquindio.reservasuq.model.entities.*;
import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import co.edu.uniquindio.reservasuq.repositories.ReservaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class ReservaServicio {
    private final ReservaRepository reservaRepository;

    public ReservaServicio() {
        reservaRepository = new ReservaRepository();
    }

    public ArrayList<Reserva> listarReservas() {
        return reservaRepository.listarReservas();
    }

    public ArrayList<Reserva> listarReservas(Usuario usuario) {
        ArrayList<Reserva> reservas = listarReservas();
        ArrayList<Reserva> reservasUsuario = new ArrayList<>();
        for (Reserva reserva : reservas) {
            if (reserva.getCliente().equals(usuario)) {
                reservasUsuario.add(reserva);
            }
        }
        return reservasUsuario;
    }

    public void agregarReserva(Alojamiento alojamiento, Cliente cliente, int numeroHuespedes, LocalDate fechainicial, int diasReserva, ArrayList<Oferta> ofertas) throws Exception {
        StringBuilder e = new StringBuilder();
        if (alojamiento == null) e.append("Seleccione un alojamiento - ");
        if (numeroHuespedes == 0) e.append("Seleccione un numero de huespedes - ");
        if (fechainicial == null) e.append("Seleccione un fecha inicial - ");
        if (diasReserva == 0) e.append("Seleccione cuantos dias de reserva - ");
        if (!e.isEmpty())throw new Exception(e+"Verifique y rellene los campos");
        if (fechainicial.isBefore(LocalDate.now())) throw new Exception("Fecha inicial no valida");
        ArrayList<LocalDate> fechas = guardarFechas(fechainicial, diasReserva);
        boolean disponible = verificarDisponibilidad(alojamiento, fechas);
        if (!disponible) throw new Exception("Este alojamiento no esta disponibles en ese rango de fechas");
        if (numeroHuespedes > alojamiento.getCapacidadHuespedes())throw new Exception("Este alojamiento no tiene capacidad para tantas personas");
        float precio = alojamiento.calcularPrecioTotal(diasReserva);
        float preciototal = verificarDescuento(alojamiento,precio,numeroHuespedes,diasReserva,fechas,ofertas);
        Factura factura = Factura.builder().fecha(LocalDateTime.now()).subtotal(precio).total(preciototal).id(UUID.randomUUID()).build();
        Reserva reserva = Reserva.builder().id(UUID.randomUUID()).alojamiento(alojamiento).cliente(cliente).numeroHuespedes(numeroHuespedes).diasReserva(fechas).Precio(preciototal).factura(factura).build();
        reservaRepository.agregarReserva(reserva);
        cliente.agregarReserva(reserva);

    }

    public ArrayList<LocalDate> guardarFechas(LocalDate fechaInicial,int dias){
        ArrayList<LocalDate> fechas = new ArrayList<>();
        for (int i = 0; i < dias; i++) {
            fechas.add(fechaInicial);
            fechaInicial = fechaInicial.plusDays(1);
        }
        return fechas;
    }

    public boolean verificarDisponibilidad(Alojamiento alojamiento, ArrayList<LocalDate> fechas){
        ArrayList<Reserva> reservas = listarReservas();
        for (Reserva reserva : reservas) {
            if (reserva.getAlojamiento().equals(alojamiento)) {
                LocalDate inicioReserva = reserva.getDiasReserva().getFirst();
                LocalDate finReserva = reserva.getDiasReserva().getLast();
                for (LocalDate fecha : fechas) {
                    if (!fecha.isBefore(inicioReserva) && !fecha.isAfter(finReserva)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public float verificarDescuento(Alojamiento alojamiento,float precio, int cantidadhuespedes, int dias, ArrayList<LocalDate>fechas, ArrayList<Oferta> ofertas) {
        float preciofinal = 0;
        for (Oferta oferta : ofertas) {
            float flag = oferta.verificarDescuento(alojamiento,precio,cantidadhuespedes,dias,fechas);
            if(flag < precio && flag> preciofinal)preciofinal = flag;
        }
        return preciofinal == 0? precio : preciofinal;
    }

    public void eliminarReserva(Reserva reserva) throws Exception {
        if (reserva == null) throw new Exception("Seleccione una reserva para cancelarla");
        reservaRepository.eliminarReserva(reserva);
        reserva.getCliente().eliminarReserva(reserva);
    }
}
