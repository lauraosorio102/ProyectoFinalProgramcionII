package co.edu.uniquindio.reservasuq.model.entities;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
        billetera = Billetera.builder().id(UUID.randomUUID()).saldo(0.0f).build();
        resenias = new ArrayList<>();
        reservas = new ArrayList<>();
    }

    public void recargarBilletera(String valorRecarga) throws Exception {
        billetera.recargarBilletera(valorRecarga);
    }

    public void cobrarBilletera(float valorRecarga) throws Exception {
        billetera.cobrarBilletera(valorRecarga);
    }

    public void agregarResenia(String titulo, String descripcion, int valoracion, Reserva reserva) throws Exception {
        StringBuilder e = new StringBuilder();
        if (titulo.isEmpty()) e.append("Rellena el titulo - ");
        if (descripcion.isEmpty()) e.append("Rellena la descripcion - ");
        if (reserva == null) e.append("Seleccione la reserva - ");
        if (!e.isEmpty())throw new Exception(e+"Rellene los datos porfavor.");
        if (reserva.getDiasReserva().getLast().isBefore(LocalDate.now()))throw new Exception("No puedes escribir una reseña si la reserva no ha terminado.");
        Resenia resenia = Resenia.builder().titulo(titulo).descripcion(descripcion).Valoracion(valoracion).build();
        resenias.add(resenia);
    }

    public void eliminarResenia(Resenia resenia) throws Exception {
        if (resenia == null) throw new Exception("Seleccione una reseña para eliminar.");
        if (!resenias.contains(resenia)) throw new Exception("No se encontró la reseña");
        resenias.remove(resenia);
    }

    public ArrayList<Resenia> listarResenias() {
        return resenias;
    }

    public void eliminarReserva(Reserva reserva) {
        reservas.remove(reserva);
    }

    public void agregarReserva(Reserva reserva) {
        reservas.add(reserva);
    }
}
