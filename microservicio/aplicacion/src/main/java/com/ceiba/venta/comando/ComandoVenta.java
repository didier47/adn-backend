package com.ceiba.venta.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoVenta {

    private Long id;
    private Long idRepartidor;
    private String referencia;
    private Long distancia;
    private LocalDate fechaEntrega;

}
