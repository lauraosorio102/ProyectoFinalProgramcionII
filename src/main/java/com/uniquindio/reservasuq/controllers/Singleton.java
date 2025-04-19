package com.uniquindio.reservasuq.controllers;

import com.uniquindio.reservasuq.model.Alojamiento;
import com.uniquindio.reservasuq.model.Usuario;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Singleton {

    private Singleton instance;
    private List<Usuario> usuario;
    private List<Alojamiento> alojamiento;
}
