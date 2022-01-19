package com.ceiba.venta.servicio.testdatabuilder;

import com.ceiba.item.modelo.entidad.Item;
import com.ceiba.item.servicio.testdatabuilder.ItemTestDataBuilder;
import com.ceiba.repartidor.modelo.entidad.Repartidor;
import com.ceiba.repartidor.servicio.testdatabuilder.RepartidorTestDataBuilder;
import com.ceiba.venta.modelo.entidad.Venta;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class VentaTestDataBuilder {

    private Long id;
    private Repartidor repartidor;
    private List<Item> items;
    private String referencia;
    private Long distancia;
    private LocalDate fechaEntrega;
    private Double valorEnvio;

    public VentaTestDataBuilder() {
        repartidor = new RepartidorTestDataBuilder().conId(2L).build();
        items = Collections.singletonList(
                new ItemTestDataBuilder().conId(1L).conCantidad(2L).build()
        );
        referencia = "referenciaventa";
        distancia = 5L;
        fechaEntrega = LocalDate.now().plusDays(1);
        valorEnvio = null;
    }

    public VentaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public VentaTestDataBuilder conRepartidor(Repartidor repartidor) {
        this.repartidor = repartidor;
        return this;
    }

    public VentaTestDataBuilder conItems(List<Item> items) {
        this.items = items;
        return this;
    }

    public VentaTestDataBuilder conReferencia(String referencia) {
        this.referencia = referencia;
        return this;
    }

    public VentaTestDataBuilder conDistancia(Long distancia) {
        this.distancia = distancia;
        return this;
    }

    public VentaTestDataBuilder conFechaEntrega(LocalDate fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
        return this;
    }

    public VentaTestDataBuilder conValorEnvio(Double valorEnvio) {
        this.valorEnvio = valorEnvio;
        return this;
    }

    public Venta build() {
        return new Venta(id, repartidor, items, referencia, distancia, fechaEntrega, valorEnvio);
    }
}
