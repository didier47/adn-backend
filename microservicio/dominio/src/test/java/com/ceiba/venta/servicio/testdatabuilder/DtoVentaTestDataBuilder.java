package com.ceiba.venta.servicio.testdatabuilder;

import com.ceiba.item.modelo.dto.DtoItem;
import com.ceiba.item.servicio.testdatabuilder.DtoItemTestDataBuilder;
import com.ceiba.repartidor.modelo.dto.DtoRepartidor;
import com.ceiba.repartidor.servicio.testdatabuilder.DtoRepartidorTestDataBuilder;
import com.ceiba.venta.modelo.dto.DtoVenta;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class DtoVentaTestDataBuilder {

    private Long id;
    private DtoRepartidor repartidor;
    private List<DtoItem> items;
    private String referencia;
    private Long distancia;
    private LocalDate fechaEntrega;
    private Double valorEnvio;

    public DtoVentaTestDataBuilder() {
        repartidor = new DtoRepartidorTestDataBuilder().conId(2L).build();
        items = Collections.singletonList(
                new DtoItemTestDataBuilder().conId(1L).conCantidad(2L).build()
        );
        referencia = "referenciaventa";
        distancia = 5L;
        fechaEntrega = LocalDate.now().plusDays(1);
        valorEnvio = null;
    }

    public DtoVentaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public DtoVentaTestDataBuilder conRepartidor(DtoRepartidor repartidor) {
        this.repartidor = repartidor;
        return this;
    }

    public DtoVentaTestDataBuilder conItems(List<DtoItem> items) {
        this.items = items;
        return this;
    }

    public DtoVentaTestDataBuilder conReferencia(String referencia) {
        this.referencia = referencia;
        return this;
    }

    public DtoVentaTestDataBuilder conDistancia(Long distancia) {
        this.distancia = distancia;
        return this;
    }

    public DtoVentaTestDataBuilder conFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
        return this;
    }

    public DtoVentaTestDataBuilder conValorEnvio(Double valorEnvio) {
        this.valorEnvio = valorEnvio;
        return this;
    }

    public DtoVenta build() {
        return new DtoVenta(id, repartidor, items, referencia, distancia, fechaEntrega, valorEnvio);
    }
}
