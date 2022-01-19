package com.ceiba.venta.modelo.entidad;


import com.ceiba.item.modelo.entidad.Item;
import com.ceiba.repartidor.modelo.entidad.Repartidor;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static com.ceiba.dominio.ValidadorArgumento.*;

@Getter
public class Venta {

    private static final String SE_DEBE_INGRESAR_EL_REPARTIDOR = "Se debe ingresar el repartidor";
    private static final String SE_DEBE_INGRESAR_LA_REFERENCIA = "Se debe ingresar la referencia de la venta";
    private static final String SE_DEBE_INGRESAR_LA_DISTANCIA = "Se debe ingresar la distancia de la venta";
    private static final String LA_DISTANCIA_DEBE_SER_POSITIVA = "La distancia debe ser positiva";
    private static final String SE_DEBE_INGRESAR_LA_FECHA_DE_ENTREGA = "Se debe ingresar la fecha de entrega de la venta";
    private static final String LA_FECHA_DE_ENTREGA_DEBE_SER_DESPUES_DE_HOY = "La fecha de entrega debe ser a partir de mañana";

    private static final long DISTANCIA_LIMITE_PARA_ENVIO_PRIMER_NIVEL = 10L;
    private static final long DISTANCIA_LIMITE_PARA_ENVIO_SEGUNDO_NIVEL = 30L;
    private static final long DISTANCIA_LIMITE_PARA_ENVIO_TERCER_NIVEL = 70L;

    private static final double VALOR_PARA_ENVIO_PRIMER_NIVEL = 10000;
    private static final double VALOR_PARA_ENVIO_SEGUNDO_NIVEL = 20000;
    private static final double VALOR_PARA_ENVIO_TERCER_NIVEL = 40000;
    private static final double VALOR_PARA_ENVIO_CUARTO_NIVEL = 80000;

    private static final double VALOR_ADICIONAL_ENVIO_DIA_NO_HABIL = 10000;

    private Long id;
    private Repartidor repartidor;
    private List<Item> items;
    private String referencia;
    private Long distancia;
    private LocalDate fechaEntrega;
    private Double valorEnvio;

    public Venta(Long id, Repartidor repartidor, List<Item> items, String referencia, Long distancia, LocalDate fechaEntrega, Double valorEnvio) {
        validarObligatorio(referencia, SE_DEBE_INGRESAR_LA_REFERENCIA);
        validarObligatorio(distancia, SE_DEBE_INGRESAR_LA_DISTANCIA);
        validarPositivo(distancia, LA_DISTANCIA_DEBE_SER_POSITIVA);
        validarObligatorio(fechaEntrega, SE_DEBE_INGRESAR_LA_FECHA_DE_ENTREGA);
        validarMenor(LocalDate.now(), fechaEntrega, LA_FECHA_DE_ENTREGA_DEBE_SER_DESPUES_DE_HOY);
        validarIgual(LocalDate.now(), fechaEntrega, LA_FECHA_DE_ENTREGA_DEBE_SER_DESPUES_DE_HOY);

        this.id = id;
        this.repartidor = repartidor;
        this.items = Collections.unmodifiableList(items);
        this.referencia = referencia;
        this.distancia = distancia;
        this.fechaEntrega = fechaEntrega;
        this.valorEnvio = valorEnvio == null ? calcularValorEnvio() : valorEnvio;
    }

    private Double calcularValorEnvio() {
        double valorCalculado = 0;
        if (fechaEntregaEsDiaNoHabil()) {
            valorCalculado = valorCalculado + VALOR_ADICIONAL_ENVIO_DIA_NO_HABIL;
        }
        if (distanciaMenorOIgual(DISTANCIA_LIMITE_PARA_ENVIO_PRIMER_NIVEL)) {
            return valorCalculado + VALOR_PARA_ENVIO_PRIMER_NIVEL;
        }
        if (distanciaMenorOIgual(DISTANCIA_LIMITE_PARA_ENVIO_SEGUNDO_NIVEL)) {
            return valorCalculado + VALOR_PARA_ENVIO_SEGUNDO_NIVEL;
        }
        if (distanciaMenorOIgual(DISTANCIA_LIMITE_PARA_ENVIO_TERCER_NIVEL)) {
            return valorCalculado + VALOR_PARA_ENVIO_TERCER_NIVEL;
        }
        return valorCalculado + VALOR_PARA_ENVIO_CUARTO_NIVEL;
    }

    private boolean distanciaMenorOIgual(Long distanciaLimite) {
        return this.distancia <= distanciaLimite;
    }

    private boolean fechaEntregaEsDiaNoHabil() {
        return this.fechaEntrega.getDayOfWeek().equals(DayOfWeek.SATURDAY) || this.fechaEntrega.getDayOfWeek().equals(DayOfWeek.SUNDAY);
    }

}
