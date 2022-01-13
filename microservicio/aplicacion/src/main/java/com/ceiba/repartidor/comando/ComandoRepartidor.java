package com.ceiba.repartidor.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoRepartidor {

    private Long id;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String telefono;

}
