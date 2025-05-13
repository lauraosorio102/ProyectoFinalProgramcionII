package co.edu.uniquindio.reservasuq.model.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Factura {
    private UUID id;
    private LocalDateTime fecha;
    private float subtotal,total;
    //codigo qr
}
