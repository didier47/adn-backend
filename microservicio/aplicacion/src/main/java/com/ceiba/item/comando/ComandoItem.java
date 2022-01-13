package com.ceiba.item.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoItem {

    private Long id;
    private String referencia;
    private String nombre;
    private Long cantidad;

}
