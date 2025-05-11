package co.edu.uniquindio.reservasuq.model;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class Billetera {
    private UUID id;
    private Float saldo;

    public void RecargarBilletera(Float recarga)throws Exception{
        if (recarga <= 0 || recarga.isNaN())throw new Exception("Valor invalido");
        saldo += recarga;
    }
}
