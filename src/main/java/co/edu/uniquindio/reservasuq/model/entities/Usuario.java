package co.edu.uniquindio.reservasuq.model.entities;

import co.edu.uniquindio.reservasuq.utils.EnvioEmail;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Usuario {
    private String correo, contrasenia;
    private boolean estado;

    public Usuario(String correo, String contrasenia) {
        this.correo = correo;
        this.contrasenia = contrasenia;
        estado = false;
    }
}
