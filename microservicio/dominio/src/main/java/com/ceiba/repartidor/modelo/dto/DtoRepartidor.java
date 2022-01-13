package com.ceiba.repartidor.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoRepartidor {

    private final Long id;
    private final String identificacion;
    private final String nombres;
    private final String apellidos;
    private final String telefono;

}
