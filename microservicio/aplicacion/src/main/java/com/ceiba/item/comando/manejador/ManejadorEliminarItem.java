package com.ceiba.item.comando.manejador;

import com.ceiba.item.servicio.ServicioEliminarItem;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarItem implements ManejadorComando<Long> {

    private final ServicioEliminarItem servicioEliminarItem;

    public ManejadorEliminarItem(ServicioEliminarItem servicioEliminarItem) {
        this.servicioEliminarItem = servicioEliminarItem;
    }

    public void ejecutar(Long idItem) {
        this.servicioEliminarItem.ejecutar(idItem);
    }
}
