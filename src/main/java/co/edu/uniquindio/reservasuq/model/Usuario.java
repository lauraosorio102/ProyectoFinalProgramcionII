package co.edu.uniquindio.reservasuq.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public abstract class Usuario {

    private String nombre;
    private String cedula;
    private String Telefono;
    private String correo;
    private String contraeña;
    private boolean activo;
    
}
