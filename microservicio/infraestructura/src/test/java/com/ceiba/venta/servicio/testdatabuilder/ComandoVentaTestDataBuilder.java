package com.ceiba.venta.servicio.testdatabuilder;

import com.ceiba.item.modelo.dto.DtoIdCantidadItem;
import com.ceiba.venta.comando.ComandoVenta;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class ComandoVentaTestDataBuilder {

    private Long id;
    private Long idRepartidor;
    private List<DtoIdCantidadItem> items;
    private String referencia;
    private Long distancia;
    private LocalDate fechaEntrega;

    public ComandoVentaTestDataBuilder() {
        idRepartidor = 2L;
        items = Collections.singletonList(new DtoIdCantidadItem(1L, 3L));
        referencia = UUID.randomUUID().toString();
        distancia = 5L;
        fechaEntrega = LocalDate.now().plusDays(1);
    }

    public ComandoVentaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public ComandoVentaTestDataBuilder conIdRepartidor(Long idRepartidor) {
        this.idRepartidor = idRepartidor;
        return this;
    }

    public ComandoVentaTestDataBuilder conItems(List<DtoIdCantidadItem> items) {
        this.items = items;
        return this;
    }

    public ComandoVentaTestDataBuilder conReferencia(String referencia) {
        this.referencia = referencia;
        return this;
    }

    public ComandoVentaTestDataBuilder conDistancia(Long distancia) {
        this.distancia = distancia;
        return this;
    }

    public ComandoVentaTestDataBuilder conFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
        return this;
    }

    public ComandoVenta build() {
        return new ComandoVenta(id, idRepartidor, items, referencia, distancia, fechaEntrega);
    }
}
