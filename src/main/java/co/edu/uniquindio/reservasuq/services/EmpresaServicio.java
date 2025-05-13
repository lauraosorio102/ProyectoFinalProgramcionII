package co.edu.uniquindio.reservasuq.services;

import co.edu.uniquindio.reservasuq.model.entities.*;
import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import co.edu.uniquindio.reservasuq.model.factory.Habitacion;
import co.edu.uniquindio.reservasuq.model.factory.Hotel;
import co.edu.uniquindio.reservasuq.services.Interface.IEmpresaServicio;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;

public class EmpresaServicio implements IEmpresaServicio {
        private final AlojamientoServicio alojamientoServicio;
        private final OfertaServicio ofertasServicio;
        private final ReservaServicio reservaServicio;
        private final UsuarioServicio usuarioServicio;

        public EmpresaServicio() {
            alojamientoServicio = new AlojamientoServicio();
            ofertasServicio = new OfertaServicio();
            reservaServicio = new ReservaServicio();
            usuarioServicio = new UsuarioServicio();
        }

    public void agregarCliente(String correo, String contrasenia, String cedula, String nombre, String telefono) throws Exception {
        usuarioServicio.agregarCliente(correo,contrasenia,cedula,nombre,telefono);
    }

    public Usuario iniciarSesion(String correo, String contrasenia) throws Exception {
        return usuarioServicio.iniciarSesion(correo,contrasenia);
    }

    public ArrayList<Usuario> listarUsuarios() {
        return usuarioServicio.listarUsuarios();
    }

    public void eliminarCliente(Cliente cliente,String correo,String contrasenia) throws Exception {
        usuarioServicio.eliminarCliente(cliente, correo, contrasenia);
    }

    public void editarCliente(Cliente cliente, String nombre, String telefono) throws Exception {
        usuarioServicio.editarCliente(cliente,nombre,telefono);
    }

    public Usuario buscarCliente(String cedula) {
        return usuarioServicio.buscarCliente(cedula);
    }

    public void cambiarContrasenia(String contraseniavieja,String contraseniaviejavalidacion, String contrasenianueva, Usuario usuario) throws Exception {
        usuarioServicio.cambiarContrasenia(contraseniavieja,contraseniaviejavalidacion,contrasenianueva,usuario);
    }

    public void agregarCasa(String nombre, String descripcion, Ciudad ciudad, Image foto, String precioporNoche, int capacidadHuespedes, String costoAdicional) throws Exception {
        alojamientoServicio.agregarCasa(nombre,descripcion,ciudad,foto,precioporNoche,capacidadHuespedes,costoAdicional);
    }

    public void agregarApartamento(String nombre, String descripcion, Ciudad ciudad, Image foto, String precioporNoche, int capacidadHuespedes, String costoAdicional) throws Exception {
        alojamientoServicio.agregarApartamento( nombre,  descripcion,  ciudad,  foto,  precioporNoche,  capacidadHuespedes, costoAdicional);
    }

    public void agregarHotel(String nombre, String descripcion, Ciudad ciudad, Image foto) throws Exception {
        alojamientoServicio.agregarHotel(nombre,descripcion,ciudad,foto);
    }

    public ArrayList<Alojamiento> listarAlojamientos() {
        return alojamientoServicio.listarAlojamientos();
    }

    public ArrayList<Alojamiento> listarAlojamientosPrincipales() {
        return alojamientoServicio.listarAlojamientosPrincipales();
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

    public void agregarOferta(String nombre, LocalDate fechainicial, int diasOferta, int cantidadhuespedes, String valorDescuento, int diasReserva) throws Exception {
        ofertasServicio.agregarOferta(nombre,fechainicial,diasOferta,cantidadhuespedes,valorDescuento,diasReserva);
    }

    public void eliminarOferta(Oferta oferta) throws Exception {
        ofertasServicio.eliminarOferta(oferta);
    }

    public Oferta buscarOferta(String nombre) {
        return ofertasServicio.buscarOferta(nombre);
    }

    public ArrayList<Oferta> listarOfertas() {
        return ofertasServicio.listarOfertas();
    }

    public void editarOferta(Oferta oferta, String nombre, LocalDate fechainicial, int diasOferta, int cantidadhuespedes, String valorDescuento,int diasReserva) throws Exception {
        ofertasServicio.editarOferta(oferta, nombre, fechainicial, diasOferta, cantidadhuespedes, valorDescuento, diasReserva);
    }

    public void agregarReserva(Alojamiento alojamiento,Cliente cliente, int numeroHuespedes, LocalDate fechainicial, int diasReserva) throws Exception {
        reservaServicio.agregarReserva(alojamiento,cliente,numeroHuespedes,fechainicial,diasReserva,listarOfertas());
        usuarioServicio.guardarDatos();
    }

    public void eliminarReserva(Reserva reserva) throws Exception {
        reservaServicio.eliminarReserva(reserva);
        usuarioServicio.guardarDatos();
    }

    public ArrayList<Reserva> listarReservas() {
        return reservaServicio.listarReservas();
    }

    public ArrayList<Reserva> listarReservas(Usuario usuario) {
        return reservaServicio.listarReservas(usuario);
    }

    public void agregarResenia(Cliente cliente,Alojamiento alojamiento, String titulo, String descripcion, int valoracion, Reserva reserva) throws Exception {
        cliente.agregarResenia( titulo,  descripcion,  valoracion, reserva);
        usuarioServicio.guardarDatos();
        alojamiento.agregarResenia( titulo,  descripcion,  valoracion);
        alojamientoServicio.guardarDatos();
    }

    public void eliminarResenia(Cliente cliente,Alojamiento alojamiento, Resenia resenia) throws Exception {
        cliente.eliminarResenia(resenia);
        usuarioServicio.guardarDatos();
        alojamiento.eliminarResenia(resenia);
        alojamientoServicio.guardarDatos();
    }

    public ArrayList<Resenia> listarResenias(Cliente cliente) {
        return cliente.listarResenias();
    }

    public void RecargarBilletera(Cliente cliente, String valorRecarga) throws Exception {
        cliente.recargarBilletera(valorRecarga);
        usuarioServicio.guardarDatos();
    }

    public void agregarHabitacion(Hotel hotel, String numeroHabitacion, String descripcion, String costoHabitacion, int capacidadhuespedes, Image foto) throws Exception {
        hotel.agregarHabitacion(numeroHabitacion,descripcion,costoHabitacion,capacidadhuespedes,foto);
        alojamientoServicio.guardarDatos();
    }

    public ArrayList<Habitacion> listarHabitaciones(Hotel hotel) {
        return hotel.listarHabitaciones();
    }

    public void eliminarHabitacion(Hotel hotel, Habitacion habitacion) throws Exception {
        hotel.eliminarHabitacion(habitacion);
        alojamientoServicio.guardarDatos();
    }

    public void agregarServicio(Alojamiento alojamiento, String servicio) throws Exception {
        alojamiento.agregarServicio(servicio);
        alojamientoServicio.guardarDatos();
    }

    public void eliminarServicio(Alojamiento alojamiento, String servicio) throws Exception {
        alojamiento.eliminarServicio(servicio);
        alojamientoServicio.guardarDatos();
    }

    public ArrayList<String> listarServicios(Alojamiento alojamiento) {
        return alojamiento.listarServicios();
    }
}
