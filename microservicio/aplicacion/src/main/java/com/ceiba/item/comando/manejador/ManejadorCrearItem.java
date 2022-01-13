package com.ceiba.item.comando.manejador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.item.comando.ComandoItem;
import com.ceiba.item.comando.fabrica.FabricaItem;
import com.ceiba.item.modelo.entidad.Item;
import com.ceiba.item.servicio.ServicioCrearItem;
import com.ceiba.manejador.ManejadorComandoRespuesta;
import org.springframework.stereotype.Component;

@Component
public class ManejadorCrearItem implements ManejadorComandoRespuesta<ComandoItem, ComandoRespuesta<Long>> {

    private final FabricaItem fabricaItem;
    private final ServicioCrearItem servicioCrearItem;

    public ManejadorCrearItem(FabricaItem fabricaItem, ServicioCrearItem servicioCrearItem) {
        this.fabricaItem = fabricaItem;
        this.servicioCrearItem = servicioCrearItem;
    }

    public ComandoRespuesta<Long> ejecutar(ComandoItem comandoItem) {
        Item item = this.fabricaItem.crear(comandoItem);
        return new ComandoRespuesta<>(this.servicioCrearItem.ejecutar(item));
    }
}
