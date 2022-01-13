package com.ceiba.venta.servicio.testdatabuilder;

import com.ceiba.venta.comando.ComandoVenta;

import java.time.LocalDate;
import java.util.UUID;

public class ComandoVentaTestDataBuilder {

    private Long id;
    private Long idRepartidor;
    private String referencia;
    private Long distancia;
    private LocalDate fechaEntrega;

    public ComandoVentaTestDataBuilder() {
        idRepartidor = 2L;
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
        return new ComandoVenta(id, idRepartidor, referencia, distancia, fechaEntrega);
    }
}
