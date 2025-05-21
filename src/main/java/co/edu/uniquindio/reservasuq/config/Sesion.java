package co.edu.uniquindio.reservasuq.config;

import co.edu.uniquindio.reservasuq.model.entities.Usuario;

public class Sesion{

    private static Sesion instance;

    private Usuario usuario;


    private Sesion(){}
    public static Sesion getInstance() {
        if (instance == null) {
            instance = new Sesion();
        }
        return instance;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void abrirSesion(Usuario usuario) {
        this.usuario = usuario;
    }

    public void cerrarSesion() {
        usuario = null;
    }
}
