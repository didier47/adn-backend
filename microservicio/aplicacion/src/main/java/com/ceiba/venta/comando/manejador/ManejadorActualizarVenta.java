package com.ceiba.venta.comando.manejador;

import com.ceiba.venta.comando.ComandoVenta;
import com.ceiba.venta.comando.fabrica.FabricaVenta;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.servicio.ServicioActualizarVenta;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarVenta implements ManejadorComando<ComandoVenta> {

    private final FabricaVenta fabricaVenta;
    private final ServicioActualizarVenta servicioActualizarVenta;

    public ManejadorActualizarVenta(FabricaVenta fabricaVenta, ServicioActualizarVenta servicioActualizarVenta) {
        this.fabricaVenta = fabricaVenta;
        this.servicioActualizarVenta = servicioActualizarVenta;
    }

    public void ejecutar(ComandoVenta comandoVenta) {
        Venta venta = this.fabricaVenta.crear(comandoVenta);
        this.servicioActualizarVenta.ejecutar(venta);
    }
}
