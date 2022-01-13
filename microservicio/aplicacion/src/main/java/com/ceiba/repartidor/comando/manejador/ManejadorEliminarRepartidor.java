package com.ceiba.repartidor.comando.manejador;

import com.ceiba.repartidor.servicio.ServicioEliminarRepartidor;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarRepartidor implements ManejadorComando<Long> {

    private final ServicioEliminarRepartidor servicioEliminarRepartidor;

    public ManejadorEliminarRepartidor(ServicioEliminarRepartidor servicioEliminarRepartidor) {
        this.servicioEliminarRepartidor = servicioEliminarRepartidor;
    }

    public void ejecutar(Long idRepartidor) {
        this.servicioEliminarRepartidor.ejecutar(idRepartidor);
    }
}
