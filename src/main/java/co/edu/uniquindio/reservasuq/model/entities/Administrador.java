package co.edu.uniquindio.reservasuq.model.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Administrador extends Usuario {
    private static Administrador instancia;

    private Administrador(String Correo,String Contrasenia) {
        super(Correo, Contrasenia);
        this.setEstado(true);
    }

    public static Administrador getInstancia(String correo,String contrasenia) {
        if (instancia == null) {
            instancia = new Administrador(correo, contrasenia);
        }
        return instancia;
    }
}
