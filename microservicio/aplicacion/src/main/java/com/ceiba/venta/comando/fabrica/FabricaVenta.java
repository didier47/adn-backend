package com.ceiba.venta.comando.fabrica;

import com.ceiba.item.servicio.ServicioInventariarItems;
import com.ceiba.repartidor.modelo.entidad.Repartidor;
import com.ceiba.repartidor.servicio.ServicioBuscarRepartidor;
import com.ceiba.venta.comando.ComandoVenta;
import com.ceiba.venta.modelo.entidad.Venta;
import org.springframework.stereotype.Component;

@Component
public class FabricaVenta {

    private final ServicioBuscarRepartidor servicioBuscarRepartidor;
    private final ServicioInventariarItems servicioInventariarItems;

    public FabricaVenta(ServicioBuscarRepartidor servicioBuscarRepartidor, ServicioInventariarItems servicioInventariarItems) {
        this.servicioBuscarRepartidor = servicioBuscarRepartidor;
        this.servicioInventariarItems = servicioInventariarItems;
    }

    public Venta crear(ComandoVenta comandoVenta) {
        return new Venta(
                comandoVenta.getId(),
                new Repartidor(servicioBuscarRepartidor.ejecutar(comandoVenta.getIdRepartidor())),
                servicioInventariarItems.ejecutar(comandoVenta.getItems(), comandoVenta.getId()),
                comandoVenta.getReferencia(),
                comandoVenta.getDistancia(),
                comandoVenta.getFechaEntrega(),
                null
        );
    }

}
