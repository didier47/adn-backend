package com.ceiba.venta.comando.fabrica;

import com.ceiba.venta.comando.ComandoVenta;
import com.ceiba.venta.modelo.entidad.Venta;
import org.springframework.stereotype.Component;

@Component
public class FabricaVenta {

    public Venta crear(ComandoVenta comandoVenta) {
        return new Venta(
                comandoVenta.getId(),
                comandoVenta.getIdRepartidor(),
                comandoVenta.getReferencia(),
                comandoVenta.getDistancia(),
                comandoVenta.getFechaEntrega(),
                null
        );
    }

}
