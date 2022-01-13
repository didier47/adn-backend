package com.ceiba.venta.modelo.dto;

import java.time.LocalDate;

public class DtoVenta {

    private final Long id;
    private final Long idRepartidor;
    private final String referencia;
    private final Long distancia;
    private final LocalDate fechaEntrega;
    private final Double valorEnvio;

    public DtoVenta(Long id, Long idRepartidor, String referencia, Long distancia, LocalDate fechaEntrega, Double valorEnvio) {
        this.id = id;
        this.idRepartidor = idRepartidor;
        this.referencia = referencia;
        this.distancia = distancia;
        this.fechaEntrega = fechaEntrega;
        this.valorEnvio = valorEnvio;
    }

    public Long getId() {
        return id;
    }

    public Long getIdRepartidor() {
        return idRepartidor;
    }

    public String getReferencia() {
        return referencia;
    }

    public Long getDistancia() {
        return distancia;
    }

    public LocalDate getFechaEntrega() {
        return fechaEntrega;
    }

    public Double getValorEnvio() {
        return valorEnvio;
    }
}
