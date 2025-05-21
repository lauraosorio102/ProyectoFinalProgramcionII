package co.edu.uniquindio.reservasuq.model.entities;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
public class Billetera implements Serializable {
    private UUID id;
    private Float saldo;

    public void recargarBilletera(float recarga)throws Exception{
        if (recarga <= 0)throw new Exception("Valor invalido");
        saldo += recarga;
    }

    public void recargarBilletera(String recarga)throws Exception{
        float valorRecarga;
        try{
             valorRecarga = Float.parseFloat(recarga);
        }catch(NumberFormatException e){
            throw new Exception("Valor invalido");
        }
        recargarBilletera(valorRecarga);
    }

    public void cobrarBilletera(float valorRecarga) throws Exception {
        if (saldo < valorRecarga) throw new Exception("Fondos insuficientes");
        saldo -= valorRecarga;
    }
}
