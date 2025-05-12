package co.edu.uniquindio.reservasuq.services.Interface;

import co.edu.uniquindio.reservasuq.model.entities.*;
import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;

public interface IEmpresaServicio {

        //Cliente Servicio
        void agregarCliente(String correo, String contrasenia, String cedula, String nombre, String telefono)throws Exception;

        void iniciarSesion(String correo, String contrasenia)throws Exception;

        ArrayList<Usuario> listarUsuarios();

        void eliminarCliente(Cliente cliente)throws Exception;

        void editarCliente(Cliente cliente, String nombre, String telefono)throws Exception;

        Usuario buscarCliente(String cedula);

        //AlojamientoServicio
        void agregarCasa(String nombre, String descripcion, Ciudad ciudad, Image foto, float precioporNoche, int capacidadHuespedes, float costoAdicional)throws Exception;

        void agregarApartamento(String nombre, String descripcion, Ciudad ciudad, Image foto, float precioporNoche, int capacidadHuespedes, float costoAdicional)throws Exception;

        void agregarHotel(String nombre, String descripcion, Ciudad ciudad, Image foto, int capacidadHuespedes)throws Exception;

        ArrayList<Alojamiento> listarAlojamientos();

        ArrayList<Alojamiento> filtrarAlojamientos(Class<?> tipoAlojamiento, Ciudad ciudad, int capacidadHuespedes);

        Alojamiento buscarAlojamiento(String nombre);

        void eliminarAlojamiento(Alojamiento alojamiento)throws Exception;

        //void editarAlojamiento(String nombre, String descripcion, Ciudad ciudad, Image foto, float precioporNoche, int capacidadHuespedes)throws Exception;

        //OfertaServicio
        void agregarOferta(String nombre, LocalDate fechainicial, int diasOferta, int cantidadhuespedes, float valorDescuento)throws Exception;

        void eliminarOferta(Oferta oferta)throws Exception;

        Oferta buscarOferta(String nombre);

        ArrayList<Oferta> listarOfertas();

        void editarOferta(Oferta oferta, String nombre, LocalDate fechainicial, int diasOferta, int cantidadhuespedes, float valorDescuento)throws Exception;

        //ReservaServicio

        void agregarReserva(Alojamiento alojamiento, int numeroHuespedes,LocalDate fechainicial, int diasReserva)throws Exception;

        void eliminarReserva(Reserva reserva)throws Exception;

        ArrayList<Reserva> listarReservas();

        void editarReserva(Reserva reserva, Alojamiento alojamiento, int numeroHuespedes,LocalDate fechainicial, int diasReserva)throws Exception;

        //Varios

        void agregarResenia(String titulo,String descripcion,int valoracion, Reserva reserva)throws Exception;

        void eliminarResenia(Resenia resenia)throws Exception;
}
