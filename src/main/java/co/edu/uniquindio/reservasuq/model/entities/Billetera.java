package co.edu.uniquindio.reservasuq.model.entities;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class Billetera {
    private UUID id;
    private Float saldo;

    public void RecargarBilletera(float recarga)throws Exception{
        if (recarga <= 0)throw new Exception("Valor invalido");
        saldo += recarga;
    }

    public void RecargarBilletera(String recarga)throws Exception{
        float valorRecarga;
        try{
             valorRecarga = Float.parseFloat(recarga);
        }catch(NumberFormatException e){
            throw new Exception("Valor invalido");
        }
        RecargarBilletera(valorRecarga);
    }
}
