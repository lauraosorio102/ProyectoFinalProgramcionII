package co.edu.uniquindio.reservasuq.services;

import co.edu.uniquindio.reservasuq.repositories.UsuarioRepository;

public class UsuarioServicio {
    private UsuarioRepository usuarioRepository;

    public UsuarioServicio() {
        usuarioRepository = new UsuarioRepository();
    }
}
