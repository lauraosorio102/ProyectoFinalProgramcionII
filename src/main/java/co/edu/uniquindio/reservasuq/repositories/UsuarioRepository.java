package co.edu.uniquindio.reservasuq.repositories;

import co.edu.uniquindio.reservasuq.model.entities.Cliente;
import co.edu.uniquindio.reservasuq.model.entities.Usuario;

import java.util.ArrayList;

public class UsuarioRepository {
    private ArrayList<Usuario> usuarios;

    public UsuarioRepository() {
        usuarios = new ArrayList<>();
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
    }

    public void cargarDatos() {
    }
}
