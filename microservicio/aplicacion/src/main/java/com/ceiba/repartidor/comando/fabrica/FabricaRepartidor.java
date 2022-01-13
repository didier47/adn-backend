package com.ceiba.repartidor.comando.fabrica;

import com.ceiba.repartidor.comando.ComandoRepartidor;
import com.ceiba.repartidor.modelo.entidad.Repartidor;
import org.springframework.stereotype.Component;

@Component
public class FabricaRepartidor {

    public Repartidor crear(ComandoRepartidor comandoRepartidor) {
        return new Repartidor(
                comandoRepartidor.getId(),
                comandoRepartidor.getIdentificacion(),
                comandoRepartidor.getNombres(),
                comandoRepartidor.getApellidos(),
                comandoRepartidor.getTelefono()
        );
    }

}
