package co.edu.uniquindio.reservasuq.services;

import co.edu.uniquindio.reservasuq.controllers.ControladorPrincipal;
import co.edu.uniquindio.reservasuq.model.entities.*;
import co.edu.uniquindio.reservasuq.model.factory.Alojamiento;
import co.edu.uniquindio.reservasuq.model.factory.Habitacion;
import co.edu.uniquindio.reservasuq.model.factory.Hotel;
import co.edu.uniquindio.reservasuq.services.Interface.IEmpresaServicio;
import javafx.scene.image.Image;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

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
            cargarAdmin();
        }

    public void cargarAdmin() {
           if (buscarUsuario("juandavidtapiero22@gmail.com") == null) {
               listarUsuarios().add(Administrador.getInstancia("juandavidtapiero22@gmail.com", "1234"));
               usuarioServicio.guardarDatos();
           }
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

    public Usuario buscarUsuario(String correo) {
        return usuarioServicio.buscarUsuario(correo);
    }

    public void cambiarContrasenia(String contrasenianueva, Usuario usuario) throws Exception {
        usuarioServicio.cambiarContrasenia(contrasenianueva,usuario);
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

    public  void agregarAlojamiento(String alojamiento, String nombre, String descripcion, Ciudad ciudad, Image foto, String precioporNoche, int capacidadHuespedes, String costoAdicional)throws Exception{
        if (alojamiento != null) {
            switch (alojamiento){
                case "Hotel" -> agregarHotel(nombre,descripcion,ciudad,foto);
                case "Casa" -> agregarCasa(nombre, descripcion, ciudad, foto, precioporNoche, capacidadHuespedes, costoAdicional);
                case "Apartamento" -> agregarApartamento(nombre, descripcion, ciudad, foto, precioporNoche, capacidadHuespedes, costoAdicional);
                default -> throw new Exception("No se pudo agregar el alojamiento");
            };
        }
    }

    public ArrayList<Alojamiento> listarAlojamientos() {
        return alojamientoServicio.listarAlojamientos();
    }

    public ArrayList<Alojamiento> listarAlojamientosPrincipales() {
        return alojamientoServicio.listarAlojamientosPrincipales();
    }

    public ArrayList<Hotel> listarHoteles() {
        return alojamientoServicio.listarHoteles();
    }

    public ArrayList<Cliente> listarClientes() {
        return usuarioServicio.listarClientes();
    }

    public ArrayList<Alojamiento> filtrarAlojamientos(Class<?> tipoAlojamiento, Ciudad ciudad, int capacidadHuespedes,String nombre) {
        return alojamientoServicio.filtrarAlojamientos(tipoAlojamiento,ciudad,capacidadHuespedes,nombre);
    }

    public Alojamiento buscarAlojamiento(String nombre) {
        return alojamientoServicio.buscarAlojamiento(nombre);
    }

    public void eliminarAlojamiento(Alojamiento alojamiento) throws Exception {
        alojamientoServicio.eliminarAlojamiento(alojamiento);
    }

    public void editarAlojamiento(Alojamiento alojamiento,String nombre, String descripcion, Ciudad ciudad, Image foto, String precioporNoche, int capacidadHuespedes) throws Exception {
        alojamientoServicio.editarAlojamiento(alojamiento,nombre, descripcion,ciudad, foto, precioporNoche, capacidadHuespedes);
    }

    public void agregarOferta(String nombre, LocalDate fechainicial, int diasOferta, int cantidadhuespedes, String valorDescuento, int diasReserva) throws Exception {
        ofertasServicio.agregarOferta(nombre,fechainicial,diasOferta,cantidadhuespedes,valorDescuento,diasReserva);
    }

    public void eliminarOferta(Oferta oferta) throws Exception {
        ofertasServicio.eliminarOferta(oferta);
    }

    public ArrayList<Oferta> filtrarOferta(String nombre) {
            return ofertasServicio.filtrarOferta(nombre);
    }


    public Oferta buscarOferta(String nombre) {
        return ofertasServicio.buscarOferta(nombre);
    }

    public ArrayList<Oferta> listarOfertas() {
        return ofertasServicio.listarOfertas();
    }

    public void añadirOfertaAlojamiento(Oferta oferta, Alojamiento alojamiento) throws Exception {
        oferta.agregarAlojamiento(alojamiento);
    }

    public void eliminarOfertaAlojamiento(Oferta oferta, Alojamiento alojamiento) throws Exception {
        oferta.eliminarAlojamiento(alojamiento);
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

    public Map<Ciudad, Alojamiento> alojamientosMasPopularesCiudad(){
            return reservaServicio.alojamientosMasPopularesCiudad();
    }

    public void agregarResenia(Cliente cliente, String titulo, String descripcion, int valoracion, Reserva reserva) throws Exception {
        cliente.agregarResenia( titulo,  descripcion,  valoracion, reserva);
        usuarioServicio.guardarDatos();
        Alojamiento alojamiento = reserva.getAlojamiento();
        alojamiento.agregarResenia(cliente, titulo,  descripcion,  valoracion);
        alojamientoServicio.guardarDatos();
    }

    public void eliminarResenia(Cliente cliente, Resenia resenia) throws Exception {
        cliente.eliminarResenia(resenia);
        usuarioServicio.guardarDatos();
        Alojamiento alojamiento = alojamientoServicio.buscarAlojamiento(resenia.getNombreAlojamiento());
        alojamiento.eliminarResenia(resenia);
        alojamientoServicio.guardarDatos();
    }

    public ArrayList<Resenia> listarResenias(Cliente cliente) {
        return cliente.listarResenias();
    }

    public void recargarBilletera(Cliente cliente, String valorRecarga) throws Exception {
        cliente.recargarBilletera(valorRecarga);
        usuarioServicio.guardarDatos();
    }

    public float consultarSaldo(Cliente cliente) {
        return usuarioServicio.consultarSaldo(cliente);
    }

    public void agregarHabitacion(Hotel hotel, String numeroHabitacion, String descripcion, String costoHabitacion, int capacidadhuespedes, Image foto) throws Exception {
        hotel.agregarHabitacion(numeroHabitacion,descripcion,costoHabitacion,capacidadhuespedes,foto);
        alojamientoServicio.guardarHabitacion(new Habitacion(numeroHabitacion,descripcion,foto,capacidadhuespedes,hotel,Float.parseFloat(costoHabitacion)));
        alojamientoServicio.guardarDatos();
    }

    public ArrayList<Habitacion> listarHabitaciones(Hotel hotel) {
        return hotel.listarHabitaciones();
    }

    public void eliminarHabitacion(Hotel hotel, Habitacion habitacion) throws Exception {
        hotel.eliminarHabitacion(habitacion);
        alojamientoServicio.eliminarHabitacion(habitacion);
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
