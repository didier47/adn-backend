package com.ceiba.item.comando.fabrica;

import com.ceiba.item.comando.ComandoItem;
import com.ceiba.item.modelo.entidad.Item;
import org.springframework.stereotype.Component;

@Component
public class FabricaItem {

    public Item crear(ComandoItem comandoItem) {
        return new Item(
                comandoItem.getId(),
                comandoItem.getReferencia(),
                comandoItem.getNombre(),
                comandoItem.getCantidad()
        );
    }

}
