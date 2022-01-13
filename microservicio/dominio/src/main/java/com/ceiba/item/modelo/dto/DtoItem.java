package com.ceiba.item.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoItem {

    private final Long id;
    private final String referencia;
    private final String nombre;
    private final Long cantidad;

}
