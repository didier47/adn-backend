package com.ceiba.repartidor.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.repartidor.comando.ComandoRepartidor;
import com.ceiba.repartidor.comando.fabrica.FabricaRepartidor;
import com.ceiba.repartidor.modelo.entidad.Repartidor;
import com.ceiba.repartidor.servicio.ServicioCrearRepartidor;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearRepartidor implements ManejadorComandoRespuesta<ComandoRepartidor, ComandoRespuesta<Long>> {

    private final FabricaRepartidor fabricaRepartidor;
    private final ServicioCrearRepartidor servicioCrearRepartidor;

    public ManejadorCrearRepartidor(FabricaRepartidor fabricaRepartidor, ServicioCrearRepartidor servicioCrearRepartidor) {
        this.fabricaRepartidor = fabricaRepartidor;
        this.servicioCrearRepartidor = servicioCrearRepartidor;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoRepartidor comandoRepartidor) {
        Repartidor repartidor = this.fabricaRepartidor.crear(comandoRepartidor);
        return new ComandoRespuesta<>(this.servicioCrearRepartidor.ejecutar(repartidor));
    }
}
