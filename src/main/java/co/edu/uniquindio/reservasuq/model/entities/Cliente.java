package co.edu.uniquindio.reservasuq.model.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.UUID;

@Getter
@Setter
public class Cliente extends Usuario {

    private String cedula,nombre,telefono;
    private Billetera billetera;
    private ArrayList<Resenia> resenias;
    private ArrayList<Reserva> reservas;

    public Cliente(String correo, String contrasenia, String cedula, String nombre, String telefono) {
        super(correo, contrasenia);
        this.cedula = cedula;
        this.nombre = nombre;
        this.telefono = telefono;
        billetera = Billetera.builder().id(UUID.randomUUID()).saldo((float) 0).build();
        resenias = new ArrayList<>();
        reservas = new ArrayList<>();
    }

}
