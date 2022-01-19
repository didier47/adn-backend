package com.ceiba.venta.comando;

import com.ceiba.item.modelo.dto.DtoIdCantidadItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoVenta {

    private Long id;
    private Long idRepartidor;
    private List<DtoIdCantidadItem> items;
    private String referencia;
    private Long distancia;
    private LocalDate fechaEntrega;

}
