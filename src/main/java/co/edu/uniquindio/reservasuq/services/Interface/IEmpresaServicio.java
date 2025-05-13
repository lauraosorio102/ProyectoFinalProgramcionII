package co.edu.uniquindio.reservasuq.services.Interface;

import co.edu.uniquindio.reservasuq.model.entities.*;
import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import co.edu.uniquindio.reservasuq.model.factory.Habitacion;
import co.edu.uniquindio.reservasuq.model.factory.Hotel;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;

public interface IEmpresaServicio {

        //Cliente Servicio
        void agregarCliente(String correo, String contrasenia, String cedula, String nombre, String telefono)throws Exception;

        Usuario iniciarSesion(String correo, String contrasenia)throws Exception;

        ArrayList<Usuario> listarUsuarios();

        void eliminarCliente(Cliente cliente,String correo,String contrasenia)throws Exception;

        void editarCliente(Cliente cliente, String nombre, String telefono)throws Exception;

        Usuario buscarCliente(String cedula);

        void cambiarContrasenia(String contraseniaantigua, String contraseniaantiguaverificacion, String contrasenianueva, Usuario usuario)throws Exception;

        //AlojamientoServicio
        void agregarCasa(String nombre, String descripcion, Ciudad ciudad, Image foto, String precioporNoche, int capacidadHuespedes, String costoAdicional)throws Exception;

        void agregarApartamento(String nombre, String descripcion, Ciudad ciudad, Image foto, String precioporNoche, int capacidadHuespedes, String costoAdicional)throws Exception;

        void agregarHotel(String nombre, String descripcion, Ciudad ciudad, Image foto)throws Exception;

        ArrayList<Alojamiento> listarAlojamientos();

        ArrayList<Alojamiento> listarAlojamientosPrincipales();

        ArrayList<Alojamiento> filtrarAlojamientos(Class<?> tipoAlojamiento, Ciudad ciudad, int capacidadHuespedes);

        Alojamiento buscarAlojamiento(String nombre);

        void eliminarAlojamiento(Alojamiento alojamiento)throws Exception;

        //void editarAlojamiento(String nombre, String descripcion, Ciudad ciudad, Image foto, float precioporNoche, int capacidadHuespedes)throws Exception;

        //OfertaServicio
        void agregarOferta(String nombre, LocalDate fechainicial, int diasOferta, int cantidadhuespedes, String valorDescuento, int diasReserva)throws Exception;

        void eliminarOferta(Oferta oferta)throws Exception;

        Oferta buscarOferta(String nombre);

        ArrayList<Oferta> listarOfertas();

        void editarOferta(Oferta oferta, String nombre, LocalDate fechainicial, int diasOferta, int cantidadhuespedes, String valorDescuento, int diasReserva)throws Exception;

        //ReservaServicio

        void agregarReserva(Alojamiento alojamiento,Cliente cliente, int numeroHuespedes,LocalDate fechainicial, int diasReserva)throws Exception;

        void eliminarReserva(Reserva reserva)throws Exception;

        ArrayList<Reserva> listarReservas();

        ArrayList<Reserva> listarReservas(Usuario usuario);

        //Varios

        void agregarResenia(Cliente cliente,Alojamiento alojamiento ,String titulo,String descripcion,int valoracion, Reserva reserva)throws Exception;

        void eliminarResenia(Cliente cliente,Alojamiento alojamiento, Resenia resenia)throws Exception;

        ArrayList<Resenia> listarResenias(Cliente cliente);

        void RecargarBilletera(Cliente cliente, String valorRecarga)throws Exception;

        void agregarHabitacion(Hotel hotel, String numeroHabitacion, String descripcion, String costoHabitacion, int capacidadhuespedes, Image foto)throws Exception;

        ArrayList<Habitacion> listarHabitaciones(Hotel hotel);

        void eliminarHabitacion(Hotel hotel, Habitacion habitacion)throws Exception;

        void agregarServicio(Alojamiento alojamiento, String servicio)throws Exception;

        void eliminarServicio(Alojamiento alojamiento, String servicio)throws Exception;

        ArrayList<String> listarServicios(Alojamiento alojamiento);
}
