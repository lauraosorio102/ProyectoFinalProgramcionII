package co.edu.uniquindio.reservasuq;

import co.edu.uniquindio.reservasuq.controllers.ControladorPrincipal;
import co.edu.uniquindio.reservasuq.model.entities.Ciudad;
import co.edu.uniquindio.reservasuq.model.entities.Cliente;
import co.edu.uniquindio.reservasuq.model.factory.Casa;
import co.edu.uniquindio.reservasuq.model.factory.Hotel;
import co.edu.uniquindio.reservasuq.services.EmpresaServicio;
import javafx.scene.image.Image;
import org.junit.jupiter.api.Test;

import java.io.InputStream;

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
}
