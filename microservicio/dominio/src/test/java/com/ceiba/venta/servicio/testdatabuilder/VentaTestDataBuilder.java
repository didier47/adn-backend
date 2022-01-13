package com.ceiba.venta.servicio.testdatabuilder;

import com.ceiba.venta.modelo.entidad.Venta;

import java.time.LocalDate;

public class VentaTestDataBuilder {

    private Long id;
    private Long idRepartidor;
    private String referencia;
    private Long distancia;
    private LocalDate fechaEntrega;
    private Double valorEnvio;

    public VentaTestDataBuilder() {
        idRepartidor = 2L;
        referencia = "referenciaventa";
        distancia = 5L;
        fechaEntrega = LocalDate.now().plusDays(1);
        valorEnvio = null;
    }

    public VentaTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public VentaTestDataBuilder conIdRepartidor(Long idRepartidor) {
        this.idRepartidor = idRepartidor;
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
        return new Venta(id, idRepartidor, referencia, distancia, fechaEntrega, valorEnvio);
    }
}
