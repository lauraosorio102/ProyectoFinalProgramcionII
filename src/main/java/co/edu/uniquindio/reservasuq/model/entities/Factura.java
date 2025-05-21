package co.edu.uniquindio.reservasuq.model.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Factura implements Serializable {
    private UUID id;
    private LocalDateTime fecha;
    private float subtotal,total;

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", subtotal=" + subtotal +
                ", total=" + total +
                '}';
    }
}
