package co.edu.uniquindio.reservasuq;

import co.edu.uniquindio.reservasuq.controllers.ControladorPrincipal;
import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import co.edu.uniquindio.reservasuq.model.entities.Cliente;
import co.edu.uniquindio.reservasuq.model.factory.Casa;
import co.edu.uniquindio.reservasuq.model.factory.Habitacion;
import co.edu.uniquindio.reservasuq.model.factory.Hotel;
import co.edu.uniquindio.reservasuq.services.EmpresaServicio;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class EmpresaServicioTest {

    EmpresaServicio empresaServicio = ControladorPrincipal.getInstancia().getEmpresaServicio();

    @Test
    public void agregarClienteTest(){
        // Test para campos vacíos
        Exception exception = assertThrows(Exception.class,
                () -> empresaServicio.agregarCliente("", "", "", "", ""));
        assertEquals("El correo no puede estar vacio - La contraseña no puede estar vacia - " +
                        "La cedula no puede estar vacia - El nombre no puede estar vacio - " +
                        "El telefono no puede estar vacio - Verifique los campos y rellene.",
                exception.getMessage());

        // Test para formatos incorrectos
        assertThrows(Exception.class,
                () -> empresaServicio.agregarCliente("Pepitoperez", "1234", "1234567890", "Pepito", "3124567890"),
                "Debería lanzar excepción por formato de correo incorrecto");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarCliente("Pepitoperez@gmail.com", "1234", "5555", "Pepito", "3124567890"),
                "Debería lanzar excepción por formato de cédula incorrecto");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarCliente("Pepitoperez@gmail.com", "1234", "1234567890", "Pepito", "1234"),
                "Debería lanzar excepción por formato de teléfono incorrecto");

        // Test para cliente ya existente
        assertThrows(Exception.class,
                () -> empresaServicio.agregarCliente("Pepitoperez@gmail.com", "1234", "1234567890", "Pepito", "3124567890"),
                "Debería lanzar excepción por cliente ya existente");

    }

    @Test
    public void agregarCasaTest() throws Exception {
        // Configuración inicial para crear una imagen válida de prueba
        InputStream inputStream = getClass().getResourceAsStream("/test-image.jpg");
        Image imagenValida = new Image(inputStream);

        // Datos de prueba válidos
        Ciudad ciudadValida = Ciudad.BOGOTA;
        String nombreValido = "Casa Campestre";
        String descripcionValida = "Hermosa casa con vista a las montañas";
        String precioValido = "150000";
        int capacidadValida = 4;
        String costoAdicionalValido = "50000";

        // Test 1: Campos obligatorios vacíos o nulos
        assertThrows(Exception.class,
                () -> empresaServicio.agregarCasa("", descripcionValida, ciudadValida, imagenValida, precioValido, capacidadValida, costoAdicionalValido),
                "Debería fallar por nombre vacío");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarCasa(nombreValido, "", ciudadValida, imagenValida, precioValido, capacidadValida, costoAdicionalValido),
                "Debería fallar por descripción vacía");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarCasa(nombreValido, descripcionValida, null, imagenValida, precioValido, capacidadValida, costoAdicionalValido),
                "Debería fallar por ciudad nula");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarCasa(nombreValido, descripcionValida, ciudadValida, null, precioValido, capacidadValida, costoAdicionalValido),
                "Debería fallar por imagen nula");

        // Test 2: Formatos numéricos inválidos
        assertThrows(Exception.class,
                () -> empresaServicio.agregarCasa(nombreValido, descripcionValida, ciudadValida,
                        imagenValida, "no_es_un_numero", capacidadValida, costoAdicionalValido),
                "Debería fallar por precio no numérico");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarCasa(nombreValido, descripcionValida, ciudadValida,
                        imagenValida, precioValido, capacidadValida, "no_es_un_numero"),
                "Debería fallar por costo adicional no numérico");

        // Test 3: Valores no positivos
        assertThrows(Exception.class,
                () -> empresaServicio.agregarCasa(nombreValido, descripcionValida, ciudadValida,
                        imagenValida, "0", capacidadValida, costoAdicionalValido),
                "Debería fallar por precio cero");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarCasa(nombreValido, descripcionValida, ciudadValida,
                        imagenValida, "-100", capacidadValida, costoAdicionalValido),
                "Debería fallar por precio negativo");

        // Test 4: Caso exitoso
        assertDoesNotThrow(
                () -> empresaServicio.agregarCasa(nombreValido, descripcionValida, ciudadValida,
                        imagenValida, precioValido, capacidadValida, costoAdicionalValido),
                "Debería aceptar datos válidos");

        // Test 5: Casa duplicada
        assertThrows(Exception.class,
                () -> empresaServicio.agregarCasa(nombreValido, descripcionValida, ciudadValida,
                        imagenValida, precioValido, capacidadValida, costoAdicionalValido),
                "Debería fallar por casa duplicada en la misma ciudad");

        empresaServicio.eliminarAlojamiento(empresaServicio.buscarAlojamiento(nombreValido));
    }

    @Test
    public void agregarApartamentoTest() throws Exception {
        // Configuración inicial para crear una imagen válida de prueba
        InputStream inputStream = getClass().getResourceAsStream("/test-image.jpg");
        Image imagenValida = new Image(inputStream);

        // Datos de prueba válidos
        Ciudad ciudadValida = Ciudad.BOGOTA;
        String nombreValido = "apartamento Campestre";
        String descripcionValida = "Hermosa casa con vista a las montañas";
        String precioValido = "150000";
        int capacidadValida = 4;
        String costoAdicionalValido = "50000";

        // Test 1: Campos obligatorios vacíos o nulos
        assertThrows(Exception.class,
                () -> empresaServicio.agregarApartamento("", descripcionValida, ciudadValida, imagenValida, precioValido, capacidadValida, costoAdicionalValido),
                "Debería fallar por nombre vacío");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarApartamento(nombreValido, "", ciudadValida, imagenValida, precioValido, capacidadValida, costoAdicionalValido),
                "Debería fallar por descripción vacía");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarApartamento(nombreValido, descripcionValida, null, imagenValida, precioValido, capacidadValida, costoAdicionalValido),
                "Debería fallar por ciudad nula");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarApartamento(nombreValido, descripcionValida, ciudadValida, null, precioValido, capacidadValida, costoAdicionalValido),
                "Debería fallar por imagen nula");

        // Test 2: Formatos numéricos inválidos
        assertThrows(Exception.class,
                () -> empresaServicio.agregarApartamento(nombreValido, descripcionValida, ciudadValida,
                        imagenValida, "no_es_un_numero", capacidadValida, costoAdicionalValido),
                "Debería fallar por precio no numérico");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarApartamento(nombreValido, descripcionValida, ciudadValida,
                        imagenValida, precioValido, capacidadValida, "no_es_un_numero"),
                "Debería fallar por costo adicional no numérico");

        // Test 3: Valores no positivos
        assertThrows(Exception.class,
                () -> empresaServicio.agregarApartamento(nombreValido, descripcionValida, ciudadValida,
                        imagenValida, "0", capacidadValida, costoAdicionalValido),
                "Debería fallar por precio cero");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarApartamento(nombreValido, descripcionValida, ciudadValida,
                        imagenValida, "-100", capacidadValida, costoAdicionalValido),
                "Debería fallar por precio negativo");

        // Test 4: Caso exitoso
        assertDoesNotThrow(
                () -> empresaServicio.agregarApartamento(nombreValido, descripcionValida, ciudadValida,
                        imagenValida, precioValido, capacidadValida, costoAdicionalValido),
                "Debería aceptar datos válidos");

        // Test 5: Apartamento duplicada
        assertThrows(Exception.class,
                () -> empresaServicio.agregarApartamento(nombreValido, descripcionValida, ciudadValida,
                        imagenValida, precioValido, capacidadValida, costoAdicionalValido),
                "Debería fallar por casa duplicada en el mismo apartamento");

        empresaServicio.eliminarAlojamiento(empresaServicio.buscarAlojamiento(nombreValido));
    }

    @Test
    public void agregarHotelTest() throws Exception {
        // Configuración inicial para crear una imagen válida de prueba
        InputStream inputStream = getClass().getResourceAsStream("/test-image.jpg");
        Image imagenValida = new Image(inputStream);

        // Datos de prueba válidos
        Ciudad ciudadValida = Ciudad.BOGOTA;
        String nombreValido = "hotel Campestre";
        String descripcionValida = "Hermosa casa con vista a las montañas";

        // Test 1: Campos obligatorios vacíos o nulos
        assertThrows(Exception.class,
                () -> empresaServicio.agregarHotel("", descripcionValida, ciudadValida, imagenValida),
                "Debería fallar por nombre vacío");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarHotel(nombreValido, "", ciudadValida, imagenValida),
                "Debería fallar por descripción vacía");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarHotel(nombreValido, descripcionValida, null, imagenValida),
                "Debería fallar por ciudad nula");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarHotel(nombreValido, descripcionValida, ciudadValida, null),
                "Debería fallar por imagen nula");

    }
    @Test
    public void agregarHabitacionTest() throws Exception {
        // Configuración inicial para crear una imagen válida de prueba
        InputStream inputStream = getClass().getResourceAsStream("/test-image.jpg");
        Image imagenValida = new Image(inputStream);

        // Datos de prueba válidos
        Ciudad ciudadValida = Ciudad.BOGOTA;
        String nombreValido = "hotel Campestre";
        String nombreValido2 = "Habitacion Campestre";
        String descripcionValida = "Hermosa casa con vista a las montañas";
        String precioValido = "150000";
        int capacidadValida = 4;

        // Test 1: Campos obligatorios vacíos o nulos
        assertThrows(Exception.class,
                () -> empresaServicio.agregarHabitacion((Hotel) empresaServicio.buscarAlojamiento(nombreValido) ,"", descripcionValida, precioValido,capacidadValida, imagenValida),
                "Debería fallar por nombre vacío");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarHabitacion((Hotel) empresaServicio.buscarAlojamiento(nombreValido) ,nombreValido2, "", precioValido,capacidadValida, imagenValida),
                "Debería fallar por descripción vacía");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarHabitacion((Hotel) empresaServicio.buscarAlojamiento(nombreValido) ,nombreValido2, descripcionValida, "",capacidadValida, imagenValida),
                "Debería fallar por precio vacío");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarHabitacion((Hotel) empresaServicio.buscarAlojamiento(nombreValido) ,nombreValido2, descripcionValida, precioValido,0, imagenValida),
                "Debería fallar por capacidadhuespedes no seleccionado");

        assertThrows(Exception.class,
                () -> empresaServicio.agregarHabitacion((Hotel) empresaServicio.buscarAlojamiento(nombreValido) ,nombreValido2, descripcionValida, precioValido,capacidadValida, null),
                "Debería fallar por foto null");
    }

    @Test
    public void agregarServicioTest() throws Exception {
        InputStream inputStream = getClass().getResourceAsStream("/test-image.jpg");
        Image imagenValida = new Image(inputStream);

        // Datos de prueba válidos
        Ciudad ciudadValida = Ciudad.BOGOTA;
        String nombreValido = "Casa Campestre";
        String descripcionValida = "Hermosa casa con vista a las montañas";
        String precioValido = "150000";
        int capacidadValida = 4;
        String costoAdicionalValido = "50000";

        if (empresaServicio.buscarAlojamiento(nombreValido) == null) {
            assertDoesNotThrow(()-> empresaServicio.agregarCasa(nombreValido,descripcionValida,ciudadValida,imagenValida,precioValido,capacidadValida,costoAdicionalValido));
        }

        // Test 1: Campos obligatorios vacios
        assertThrows(Exception.class, ()-> empresaServicio.agregarServicio(empresaServicio.buscarAlojamiento(nombreValido),""), "Deberia fallar por servicio vacio");

        //Test 2: Datos correctos
        assertDoesNotThrow(()-> empresaServicio.agregarServicio(empresaServicio.buscarAlojamiento(nombreValido),"Wifi"), "Deberia fallar por servicio vacio");

        assertThrows(Exception.class,()-> empresaServicio.agregarServicio(empresaServicio.buscarAlojamiento(nombreValido),"Wifi"), "Deberia fallar por servicio vacio");

        empresaServicio.eliminarAlojamiento(empresaServicio.buscarAlojamiento(nombreValido));
    }

    @Test
    public void agregarOfertaTest() throws Exception {
// Datos válidos de prueba
        String nombreValido = "Promo Temporada Baja";
        LocalDate fechaInicialValida = LocalDate.now().plusDays(1);
        int diasOfertaValido = 5;
        int cantidadHuespedesValida = 3;
        String valorDescuentoValido = "25.5";
        int diasReservaValido = 2;

        // Test 1: Nombre vacío
        assertThrows(Exception.class, () ->
                        empresaServicio.agregarOferta("", fechaInicialValida, diasOfertaValido, cantidadHuespedesValida, valorDescuentoValido, diasReservaValido),
                "Debería fallar por nombre vacío");

        // Test 2: Descuento vacío
        assertThrows(Exception.class, () ->
                        empresaServicio.agregarOferta(nombreValido, fechaInicialValida, diasOfertaValido, cantidadHuespedesValida, "", diasReservaValido),
                "Debería fallar por descuento vacío");

        // Test 3: Descuento no numérico
        assertThrows(Exception.class, () ->
                        empresaServicio.agregarOferta(nombreValido, fechaInicialValida, diasOfertaValido, cantidadHuespedesValida, "abc", diasReservaValido),
                "Debería fallar por descuento no numérico");

        // Test 4: Descuento <= 0
        assertThrows(Exception.class, () ->
                        empresaServicio.agregarOferta(nombreValido, fechaInicialValida, diasOfertaValido, cantidadHuespedesValida, "0", diasReservaValido),
                "Debería fallar por descuento igual a 0");

        // Test 5: Descuento > 100
        assertThrows(Exception.class, () ->
                        empresaServicio.agregarOferta(nombreValido, fechaInicialValida, diasOfertaValido, cantidadHuespedesValida, "150", diasReservaValido),
                "Debería fallar por descuento mayor a 100");

        // Test 6: Fecha inicial en el pasado
        LocalDate fechaInvalida = LocalDate.now().minusDays(1);
        assertThrows(Exception.class, () ->
                        empresaServicio.agregarOferta(nombreValido, fechaInvalida, diasOfertaValido, cantidadHuespedesValida, valorDescuentoValido, diasReservaValido),
                "Debería fallar por fecha inicial inválida");


        if (empresaServicio.buscarOferta(nombreValido) == null) {
            // Test 7: Caso exitoso
            assertDoesNotThrow(() ->
                            empresaServicio.agregarOferta(nombreValido, fechaInicialValida, diasOfertaValido, cantidadHuespedesValida, valorDescuentoValido, diasReservaValido),
                    "Debería permitir agregar una oferta válida");
        }
        empresaServicio.eliminarOferta(empresaServicio.buscarOferta(nombreValido));
        }

}
