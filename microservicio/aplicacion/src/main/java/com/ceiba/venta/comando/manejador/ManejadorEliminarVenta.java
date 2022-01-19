package com.ceiba.venta.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.venta.servicio.ServicioEliminarVenta;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarVenta implements ManejadorComando<Long> {

    private final ServicioEliminarVenta servicioEliminarVenta;

    public ManejadorEliminarVenta(ServicioEliminarVenta servicioEliminarVenta) {
        this.servicioEliminarVenta = servicioEliminarVenta;
    }

    public void ejecutar(Long idVenta) {
        this.servicioEliminarVenta.ejecutar(idVenta);
    }
}
