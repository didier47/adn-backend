package com.ceiba.venta.comando.fabrica;

import com.ceiba.repartidor.modelo.entidad.Repartidor;
import com.ceiba.repartidor.servicio.ServicioBuscarRepartidor;
import com.ceiba.venta.comando.ComandoVenta;
import com.ceiba.venta.modelo.entidad.Venta;
import org.springframework.stereotype.Component;

@Component
public class FabricaVenta {

    private final ServicioBuscarRepartidor servicioBuscarRepartidor;

    public FabricaVenta(ServicioBuscarRepartidor servicioBuscarRepartidor) {
        this.servicioBuscarRepartidor = servicioBuscarRepartidor;
    }

    public Venta crear(ComandoVenta comandoVenta) {
        return new Venta(
                comandoVenta.getId(),
                new Repartidor(servicioBuscarRepartidor.ejecutar(comandoVenta.getIdRepartidor())),
                comandoVenta.getReferencia(),
                comandoVenta.getDistancia(),
                comandoVenta.getFechaEntrega(),
                null
        );
    }

}
