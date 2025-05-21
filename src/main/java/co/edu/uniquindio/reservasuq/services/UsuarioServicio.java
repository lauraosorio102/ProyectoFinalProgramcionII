package co.edu.uniquindio.reservasuq.services;

import co.edu.uniquindio.reservasuq.model.entities.Cliente;
import co.edu.uniquindio.reservasuq.model.entities.Usuario;
import co.edu.uniquindio.reservasuq.repositories.UsuarioRepository;
import co.edu.uniquindio.reservasuq.utils.EnvioEmail;
import co.edu.uniquindio.reservasuq.utils.ValidacionCodigo;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class UsuarioServicio {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServicio() {
        usuarioRepository = new UsuarioRepository();
    }


    public void agregarCliente(String correo, String contrasenia, String cedula, String nombre, String telefono) throws Exception {
        StringBuilder e =  new StringBuilder();
        if (correo.isEmpty()) e.append("El correo no puede estar vacio - ");
        if (contrasenia.isEmpty())e.append("La contraseña no puede estar vacia - ");
        if (cedula.isEmpty())e.append("La cedula no puede estar vacia - ");
        if (nombre.isEmpty())e.append("El nombre no puede estar vacio - ");
        if (telefono.isEmpty())e.append("El telefono no puede estar vacio - ");
        if (!e.isEmpty())throw new Exception(e + "Verifique los campos y rellene.");
        if (!Pattern.matches("^\\d{10}$", telefono)) e.append("telefono no valido - ");
        if (!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", nombre)) e.append("Nombre invalido - ");
        if (!Pattern.matches("^\\d{10}$", cedula)) e.append("Cedula invalida - ");
        if (!Pattern.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", correo)) e.append("Email invalido - ");
        if (!e.isEmpty())throw new Exception(e + "Verifique los campos y rellene.");
        if (buscarCliente(cedula) != null) throw new Exception("Ya existe un cliente registrado con esa cedula");
        if (buscarUsuario(correo) != null) throw new Exception("Ya existe un usuario registrado con ese correo");
        Cliente cliente = new Cliente(correo, contrasenia, cedula, nombre, telefono);
        usuarioRepository.agregarCliente(cliente);

    }

    public ArrayList<Usuario> listarUsuarios() {
        return usuarioRepository.listarUsuarios();
    }

    public Usuario buscarCliente(String cedula) {
        ArrayList<Usuario> usuarios = listarUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Cliente){
                if (((Cliente) usuario).getCedula().equals(cedula)) {
                    return usuario;
                }
            }
        }
        return null;
    }

    public Usuario buscarUsuario(String correo) {
        ArrayList<Usuario> usuarios = listarUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo)) {
                return usuario;
            }
        }
        return null;
    }

    public Usuario iniciarSesion(String correo, String contrasenia) throws Exception {
        StringBuilder e =  new StringBuilder();
        if (correo.isEmpty()) e.append("El correo no puede estar vacio - ");
        if (contrasenia.isEmpty())e.append("La contraseña no puede estar vacia - ");
        if (!e.isEmpty())throw new Exception(e + "Verifique los campos porfavor.");
        Usuario usuario = verificarCredenciales(correo, contrasenia);
        if (usuario == null) throw new Exception("No se encontró un cliente con esas credenciales");
        if (!usuario.isEstado()){
            String codigo = ValidacionCodigo.generarCodigo();
            EnvioEmail.enviarNotificacion(correo, "Codigo de verificación", "Su codigo de verificación es:" + codigo );
            String codigoinput = ValidacionCodigo.pedirCodigoPorInterfaz();
            if (codigoinput.equals(codigo)){
                usuario.setEstado(true);
                usuarioRepository.guardarDatos();
            }else{
                throw new Exception("Codigo de verificación incorrecto");
            }
        }
        return usuario;
    }

    public Usuario verificarCredenciales(String correo, String contrasenia){
        ArrayList<Usuario> usuarios = listarUsuarios();
        for (Usuario usuario : usuarios) {
            if (usuario.getCorreo().equalsIgnoreCase(correo) && usuario.getContrasenia().equals(contrasenia)) {
                return usuario;
            }
        }
        return null;
    }

    public void cambiarContrasenia(String contrasenianueva, Usuario usuario)throws Exception {
        if (contrasenianueva.isEmpty())throw  new Exception("Rellene todos los campos");
        if (contrasenianueva.equals(usuario.getContrasenia()))throw new Exception("La contraseña nueva debe ser diferente a la actual");
        String codigo = ValidacionCodigo.generarCodigo();
        EnvioEmail.enviarNotificacion(usuario.getCorreo(), "Codigo de verificación", "Su codigo de verificación es:" + codigo );
        String codigoinput = ValidacionCodigo.pedirCodigoPorInterfaz();
        if (codigoinput.equals(codigo)){
            usuario.setContrasenia(contrasenianueva);
            usuarioRepository.guardarDatos();
        }else{
            throw new Exception("Codigo de verificación incorrecto");
        }
    }

    public void eliminarCliente(Cliente cliente, String correo, String contrasenia) throws Exception {
        if (correo.isEmpty()||contrasenia.isEmpty()) throw new Exception("Rellene los datos.");
        if (verificarCredenciales(correo,contrasenia)== null||!cliente.equals(verificarCredenciales(correo,contrasenia))) throw new Exception("Credenciales invalidas");
        cliente.setEstado(false);
    }

    public void editarCliente(Cliente cliente, String nombre, String telefono)throws Exception {
        if (cliente == null)throw new Exception("Seleccione un cliente");
        if (nombre.isEmpty()||telefono.isEmpty())throw new Exception("Rellene los datos.");
        StringBuilder e = new StringBuilder();
        if (!Pattern.matches("^\\d{10}$", telefono)) e.append("telefono no valido - ");
        if (!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$", nombre)) e.append("Nombre invalido - ");
        if (!e.isEmpty())throw new Exception(e + "Verifique los campos y rellene.");
        cliente.setNombre(nombre);
        cliente.setTelefono(telefono);
        usuarioRepository.guardarDatos();
    }

    public void guardarDatos(){
        usuarioRepository.guardarDatos();
    }

    public float consultarSaldo(Cliente cliente) {
        return cliente.consultarSaldo();
    }

    public ArrayList<Cliente> listarClientes() {
        ArrayList<Usuario> usuarios = listarUsuarios();
        ArrayList<Cliente> clientes = new ArrayList<>();
        for (Usuario usuario : usuarios) {
            if (usuario instanceof Cliente) {
                clientes.add((Cliente) usuario);
            }
        }
        return clientes;
    }
}
