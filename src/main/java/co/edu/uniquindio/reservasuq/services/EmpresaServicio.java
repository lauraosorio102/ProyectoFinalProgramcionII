package co.edu.uniquindio.reservasuq.services;

import co.edu.uniquindio.reservasuq.model.entities.*;
import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import co.edu.uniquindio.reservasuq.services.Interface.IEmpresaServicio;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;

public class EmpresaServicio implements IEmpresaServicio {
        private AlojamientoServicio alojamientoServicio;
        private OfertaServicio ofertasServicio;
        private ReservaServicio reservaServicio;
        private UsuarioServicio usuarioServicio;

        public EmpresaServicio() {
            alojamientoServicio = new AlojamientoServicio();
            ofertasServicio = new OfertaServicio();
            reservaServicio = new ReservaServicio();
            usuarioServicio = new UsuarioServicio();
        }

    public void agregarCliente(String correo, String contrasenia, String cedula, String nombre, String telefono) throws Exception {
        usuarioServicio.agregarCliente(correo,contrasenia,cedula,nombre,telefono);
    }

    public void iniciarSesion(String correo, String contrasenia) throws Exception {
        usuarioServicio.iniciarSesion(correo,contrasenia);
    }

    public ArrayList<Usuario> listarUsuarios() {
        return usuarioServicio.listarUsuarios();
    }

    public void eliminarCliente(Cliente cliente) throws Exception {
        usuarioServicio.eliminarCliente(cliente);
    }

    public void editarCliente(Cliente cliente, String nombre, String telefono) throws Exception {
        usuarioServicio.editarCliente(cliente,nombre,telefono);
    }

    public Usuario buscarCliente(String cedula) {
        return usuarioServicio.buscarCliente(cedula);
    }

    public void agregarCasa(String nombre, String descripcion, Ciudad ciudad, Image foto, float precioporNoche, int capacidadHuespedes, float costoAdicional) throws Exception {
        alojamientoServicio.agregarCasa(nombre,descripcion,ciudad,foto,precioporNoche,capacidadHuespedes,costoAdicional);
    }

    public void agregarApartamento(String nombre, String descripcion, Ciudad ciudad, Image foto, float precioporNoche, int capacidadHuespedes, float costoAdicional) throws Exception {
        alojamientoServicio.agregarApartamento( nombre,  descripcion,  ciudad,  foto,  precioporNoche,  capacidadHuespedes, costoAdicional);
    }

    public void agregarHotel(String nombre, String descripcion, Ciudad ciudad, Image foto, int capacidadHuespedes) throws Exception {
        alojamientoServicio.agregarHotel(nombre,descripcion,ciudad,foto,capacidadHuespedes);
    }

    public ArrayList<Alojamiento> listarAlojamientos() {
        return alojamientoServicio.listarAlojamientos();
    }

    public ArrayList<Alojamiento> filtrarAlojamientos(Class<?> tipoAlojamiento, Ciudad ciudad, int capacidadHuespedes) {
        return alojamientoServicio.filtrarAlojamientos(tipoAlojamiento,ciudad,capacidadHuespedes);
    }

    public Alojamiento buscarAlojamiento(String nombre) {
        return alojamientoServicio.buscarAlojamiento(nombre);
    }

    public void eliminarAlojamiento(Alojamiento alojamiento) throws Exception {
        alojamientoServicio.eliminarAlojamiento(alojamiento);
    }

    public void agregarOferta(String nombre, LocalDate fechainicial, int diasOferta, int cantidadhuespedes, float valorDescuento) throws Exception {
        ofertasServicio.agregarOferta(nombre,fechainicial,diasOferta,cantidadhuespedes,valorDescuento);
    }

    public void eliminarOferta(Oferta oferta) throws Exception {
        ofertasServicio.eliminarOferta(oferta);
    }

    public Oferta buscarOferta(String nombre) {
        return ofertasServicio.buscarOferta;
    }

    public ArrayList<Oferta> listarOfertas() {
        return ofertasServicio.listarOfertas();
    }

    public void editarOferta(Oferta oferta, String nombre, LocalDate fechainicial, int diasOferta, int cantidadhuespedes, float valorDescuento) throws Exception {
        return ofertasServicio.editarOferta(oferta,nombre,fechainicial,diasOferta,cantidadhuespedes,valorDescuento);
    }

    public void agregarReserva(Alojamiento alojamiento, int numeroHuespedes, LocalDate fechainicial, int diasReserva) throws Exception {
        reservaServicio.agregarReserva(alojamiento,numeroHuespedes,fechainicial,diasReserva,listarOfertas());
    }

    public void eliminarReserva(Reserva reserva) throws Exception {
        reservaServicio.eliminarReserva(reserva);
    }

    public ArrayList<Reserva> listarReservas() {
        return reservaServicio.listarReservas();
    }

    public void editarReserva(Reserva reserva, Alojamiento alojamiento, int numeroHuespedes, LocalDate fechainicial, int diasReserva) throws Exception {
        reservaServicio.editarReserva(reserva,alojamiento,numeroHuespedes,fechainicial,diasReserva,listarOfertas());
    }

    public void agregarResenia(String titulo, String descripcion, int valoracion, Reserva reserva) throws Exception {
        usuarioServicio.agregarResenia(titulo,descripcion,valoracion,reserva);
        alojamientoServicio.agregarResenia(titulo,descripcion,valoracion,reserva);
    }

    public void eliminarResenia(Resenia resenia) throws Exception {
        usuarioServicio.eliminarResenia(resenia);
        alojamientoServicio.eliminarResenia(resenia);
    }
}
