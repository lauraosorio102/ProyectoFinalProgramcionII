package co.edu.uniquindio.reservasuq.services;

import co.edu.uniquindio.reservasuq.model.entities.*;
import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import co.edu.uniquindio.reservasuq.model.factory.Habitacion;
import co.edu.uniquindio.reservasuq.model.factory.Hotel;
import co.edu.uniquindio.reservasuq.repositories.ReservaRepository;
import co.edu.uniquindio.reservasuq.utils.EnvioEmail;
import co.edu.uniquindio.reservasuq.utils.GeneradorQR;
import jakarta.activation.DataSource;
import jakarta.mail.util.ByteArrayDataSource;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

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
            if (reserva.getCliente().getCorreo().equals(usuario.getCorreo())) {
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
        if (alojamiento instanceof Hotel){
            for (Habitacion habitacion: ((Hotel) alojamiento).getHabitaciones()){
                disponible = verificarDisponibilidad(habitacion, fechas);
                if (!disponible)throw new Exception("Este alojamiento no esta disponibles en ese rango de fechas");
            }
        }
        if (numeroHuespedes > alojamiento.getCapacidadHuespedes())throw new Exception("Este alojamiento no tiene capacidad para tantas personas");
        float precio = alojamiento.calcularPrecioTotal(diasReserva);
        float preciototal = verificarDescuento(alojamiento,precio,numeroHuespedes,diasReserva,fechas,ofertas);
        cliente.cobrarBilletera(preciototal);
        Factura factura = Factura.builder().fecha(LocalDateTime.now()).subtotal(precio).total(preciototal).id(UUID.randomUUID()).build();
        Reserva reserva = Reserva.builder().id(UUID.randomUUID()).alojamiento(alojamiento).cliente(cliente).numeroHuespedes(numeroHuespedes).diasReserva(fechas).Precio(preciototal).factura(factura).build();
        reservaRepository.agregarReserva(reserva);
        cliente.agregarReserva(reserva);
        byte[] imagenQR = GeneradorQR.generarQRComoBytes(factura.getId().toString(), 300, 300);
        DataSource ds = new ByteArrayDataSource(imagenQR, "image/png");
        EnvioEmail.enviarNotificacionImagen(cliente.getCorreo(),"Aqui tiene su factura de la reserva realizada","Reserva:\n"+reserva.toString()+"\nFactura:\n" +factura.toString() ,ds);
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
        if (reserva.getDiasReserva().getFirst().isBefore(LocalDate.now())) throw new Exception("No puede cancelar la reserva sin tiempo de anticipación");
        reservaRepository.eliminarReserva(reserva);
        reserva.getCliente().eliminarReserva(reserva);
        reserva.getCliente().recargarBilletera(""+reserva.getFactura().getTotal());
        EnvioEmail.enviarNotificacion(reserva.getCliente().getCorreo(),"Cancelacion de Reserva", "Reserva:\n"+reserva.toString());
    }

    public Map<Ciudad, Alojamiento> alojamientosMasPopularesCiudad(){
        ArrayList<Reserva>reservas = listarReservas();
        Map<Alojamiento, Integer> alojamientos = new HashMap<>();
        for (Reserva reserva : reservas) {
            Alojamiento alojamiento = reserva.getAlojamiento();
            if (alojamiento instanceof Habitacion) alojamiento = ((Habitacion) alojamiento).getHotel();
            alojamientos.put(alojamiento,alojamientos.getOrDefault(alojamiento,0)+1);
        }
        Map<Ciudad, Alojamiento> popularesPorCiudad = new HashMap<>();
        Map<Ciudad, Integer> maxReservasPorCiudad = new HashMap<>();
        for (Map.Entry<Alojamiento, Integer> entry : alojamientos.entrySet()) {
            Alojamiento alojamiento = entry.getKey();
            Ciudad ciudad = alojamiento.getCiudad();
            int cantidad = entry.getValue();

            if (!popularesPorCiudad.containsKey(ciudad)|| cantidad > maxReservasPorCiudad.get(ciudad)) {
                popularesPorCiudad.put(ciudad,alojamiento);
                maxReservasPorCiudad.put(ciudad,cantidad);
            }
        }
        return popularesPorCiudad;
    }

    public Map<Alojamiento, Float> gananciasTotales() {
        ArrayList<Reserva>reservas = listarReservas();
        Map<Alojamiento, Float> alojamientos = new HashMap<>();
        for (Reserva reserva : reservas) {
            Alojamiento alojamiento = reserva.getAlojamiento();
            if (alojamiento instanceof Habitacion) alojamiento = ((Habitacion) alojamiento).getHotel();
            alojamientos.put(alojamiento, alojamientos.getOrDefault(alojamiento, 0f) + reserva.getPrecio());
        }
        return alojamientos;
    }


    public Map<Class<?>, Float> tipoAlojamientoMasRentables() {
        ArrayList<Reserva>reservas = listarReservas();
        Map<Class<?>, Float> alojamientos = new HashMap<>();
        for (Reserva reserva : reservas) {
            Alojamiento alojamiento = reserva.getAlojamiento();
            if (alojamiento instanceof Habitacion) alojamiento = ((Habitacion) alojamiento).getHotel();
            alojamientos.put(alojamiento.getClass(), alojamientos.getOrDefault(alojamiento.getClass(), 0f) + reserva.getPrecio());
        }
        return alojamientos;
    }

    public Map<Alojamiento, Float> ocupacionPorcentual(LocalDate fechainicial, LocalDate fechaFinal) {
        ArrayList<Reserva> reservas = listarReservas();
        Map<Alojamiento, Long> nochesReservadasPorAlojamiento = new HashMap<>();
        Map<Alojamiento, Float> ocupacionPorcentual = new HashMap<>();

        long totalNoches = ChronoUnit.DAYS.between(fechainicial, fechaFinal);
        if (totalNoches == 0) totalNoches = 1;

        for (Reserva reserva : reservas) {
            Alojamiento alojamiento = reserva.getAlojamiento();
            if (alojamiento instanceof Habitacion) alojamiento = ((Habitacion) alojamiento).getHotel();
            List<LocalDate> dias = reserva.getDiasReserva();

            LocalDate inicio = dias.getFirst().isBefore(fechainicial) ? fechainicial : dias.getFirst();
            LocalDate fin = dias.getLast().isAfter(fechaFinal) ? fechaFinal : dias.getLast();

            if (!inicio.isAfter(fin)) {
                long nochesReservadas = ChronoUnit.DAYS.between(inicio, fin);
                nochesReservadasPorAlojamiento.put(alojamiento,
                        nochesReservadasPorAlojamiento.getOrDefault(alojamiento, 0L) + nochesReservadas);
            }
        }

        for (Map.Entry<Alojamiento, Long> entry : nochesReservadasPorAlojamiento.entrySet()) {
            float porcentaje = (entry.getValue() * 100f) / totalNoches;
            ocupacionPorcentual.put(entry.getKey(), porcentaje);
        }

        return ocupacionPorcentual;
    }

    public Map<Alojamiento, Float> promedioValoracion() {
        ArrayList<Reserva> reservas = listarReservas();
        Map<Alojamiento, Float> sumatoria = new HashMap<>();
        Map<Alojamiento, Integer> contador = new HashMap<>();
        for (Reserva reserva : reservas) {
            Alojamiento alojamiento = reserva.getAlojamiento();
            if (alojamiento instanceof Habitacion) alojamiento = ((Habitacion) alojamiento).getHotel();
            List<Resenia> resenias = alojamiento.getResenias();
            if (resenias == null || resenias.isEmpty()) continue;
            for (Resenia resenia : resenias) {
                float sumaActual = sumatoria.getOrDefault(alojamiento, 0f);
                int contadorActual = contador.getOrDefault(alojamiento, 0);
                sumatoria.put(alojamiento, sumaActual + resenia.getValoracion());
                contador.put(alojamiento, contadorActual + 1);
            }
        }
        Map<Alojamiento, Float> promedioValoracion = new HashMap<>();
        for (Alojamiento alojamiento : sumatoria.keySet()) {
            float suma = sumatoria.get(alojamiento);
            int cantidad = contador.get(alojamiento);
            promedioValoracion.put(alojamiento, suma / cantidad);
        }
        return promedioValoracion;
    }
}
