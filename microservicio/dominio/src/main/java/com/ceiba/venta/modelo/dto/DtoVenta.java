package com.ceiba.venta.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class DtoVenta {

    private final Long id;
    private final Long idRepartidor;
    private final String referencia;
    private final Long distancia;
    private final LocalDate fechaEntrega;
    private final Double valorEnvio;

}
