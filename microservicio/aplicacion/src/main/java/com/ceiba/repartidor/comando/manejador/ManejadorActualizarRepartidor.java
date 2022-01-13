package com.ceiba.repartidor.comando.manejador;

import com.ceiba.repartidor.comando.ComandoRepartidor;
import com.ceiba.repartidor.comando.fabrica.FabricaRepartidor;
import com.ceiba.repartidor.modelo.entidad.Repartidor;
import com.ceiba.repartidor.servicio.ServicioActualizarRepartidor;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarRepartidor implements ManejadorComando<ComandoRepartidor> {

    private final FabricaRepartidor fabricaRepartidor;
    private final ServicioActualizarRepartidor servicioActualizarRepartidor;

    public ManejadorActualizarRepartidor(FabricaRepartidor fabricaRepartidor, ServicioActualizarRepartidor servicioActualizarRepartidor) {
        this.fabricaRepartidor = fabricaRepartidor;
        this.servicioActualizarRepartidor = servicioActualizarRepartidor;
    }

    public void ejecutar(ComandoRepartidor comandoRepartidor) {
        Repartidor repartidor = this.fabricaRepartidor.crear(comandoRepartidor);
        this.servicioActualizarRepartidor.ejecutar(repartidor);
    }
}
