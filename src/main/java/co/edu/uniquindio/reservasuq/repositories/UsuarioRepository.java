package co.edu.uniquindio.reservasuq.repositories;

import co.edu.uniquindio.reservasuq.model.entities.Cliente;
import co.edu.uniquindio.reservasuq.model.entities.Usuario;
import co.edu.uniquindio.reservasuq.utils.Constantes;
import co.edu.uniquindio.reservasuq.utils.Persistencia;

import java.io.IOException;
import java.util.ArrayList;

public class UsuarioRepository {
    private ArrayList<Usuario> usuarios;

    public UsuarioRepository() {
        usuarios = leerDatos();
    }

    public ArrayList<Usuario> listarUsuarios() {
        return usuarios;
    }

    public void agregarCliente(Cliente cliente) {
        usuarios.add(cliente);
        guardarDatos();
    }

    public void eliminarCliente(Cliente cliente) {
        usuarios.remove(cliente);
        guardarDatos();
    }

    public void guardarDatos() {
        try {
            Persistencia.serializarObjeto(Constantes.RUTA_USUARIOS, usuarios);
        } catch (IOException e) {
            System.err.println("Error guardando usuarios: " + e.getMessage());
        }
    }

    public ArrayList<Usuario> leerDatos() {
        try {
            Object datos = Persistencia.deserializarObjeto(Constantes.RUTA_USUARIOS);
            if (datos != null) {
                return (ArrayList<Usuario>) datos;
            }
        } catch (Exception e) {
            System.err.println("Error cargando usuarios: " + e.getMessage());
        }
        return new ArrayList<>();
    }
    public void cargarDatos() {
    }
}
