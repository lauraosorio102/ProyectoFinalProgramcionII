package co.edu.uniquindio.reservasuq.model;

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

    //Esto no va aquí, va en UsuarioServicio, luego lo muevo
    public Usuario iniciarSesion(String correo,String contrasenia)throws Exception{
        StringBuilder e =  new StringBuilder();
        if (correo.isEmpty()) e.append("El correo no puede estar vacio - ");
        if (contrasenia.isEmpty()) e.append("La contraseña no puede estar vacia - ");
        if (e.isEmpty()) throw new Exception (e + "Verifique los campos y rellenelos");
        if (!correo.equalsIgnoreCase(this.correo)|| !contrasenia.equals(this.contrasenia)) throw new Exception("No se encontró ningún usuario con esas credenciales");
        if (!estado){
            enviarCodigo();
        }
        return (this);
    }

    public void enviarCodigo(){
        EnvioEmail.enviarNotificacion(correo,"Activación de cuenta","");
    }
}
