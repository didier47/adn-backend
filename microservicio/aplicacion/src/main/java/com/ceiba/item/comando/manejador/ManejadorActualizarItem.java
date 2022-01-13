package com.ceiba.item.comando.manejador;

import com.ceiba.item.comando.ComandoItem;
import com.ceiba.item.comando.fabrica.FabricaItem;
import com.ceiba.item.modelo.entidad.Item;
import com.ceiba.item.servicio.ServicioActualizarItem;
import com.ceiba.manejador.ManejadorComando;
import org.springframework.stereotype.Component;

@Component
public class ManejadorActualizarItem implements ManejadorComando<ComandoItem> {

    private final FabricaItem fabricaItem;
    private final ServicioActualizarItem servicioActualizarItem;

    public ManejadorActualizarItem(FabricaItem fabricaItem, ServicioActualizarItem servicioActualizarItem) {
        this.fabricaItem = fabricaItem;
        this.servicioActualizarItem = servicioActualizarItem;
    }

    public void ejecutar(ComandoItem comandoItem) {
        Item item = this.fabricaItem.crear(comandoItem);
        this.servicioActualizarItem.ejecutar(item);
    }
}
