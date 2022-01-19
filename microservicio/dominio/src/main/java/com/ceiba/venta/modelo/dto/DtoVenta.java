package com.ceiba.venta.modelo.dto;

import com.ceiba.item.modelo.dto.DtoItem;
import com.ceiba.repartidor.modelo.dto.DtoRepartidor;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
@AllArgsConstructor
public class DtoVenta {

    private final Long id;
    private final DtoRepartidor repartidor;
    private final List<DtoItem> items;
    private final String referencia;
    private final Long distancia;
    private final LocalDate fechaEntrega;
    private final Double valorEnvio;

}
