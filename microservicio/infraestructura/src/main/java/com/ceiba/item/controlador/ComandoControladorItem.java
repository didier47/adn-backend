package com.ceiba.item.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.item.comando.ComandoItem;
import com.ceiba.item.comando.manejador.ManejadorActualizarItem;
import com.ceiba.item.comando.manejador.ManejadorCrearItem;
import com.ceiba.item.comando.manejador.ManejadorEliminarItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/items")
@Api(tags = {"Controlador comando item"})
public class ComandoControladorItem {

    private final ManejadorCrearItem manejadorCrearItem;
    private final ManejadorEliminarItem manejadorEliminarItem;
    private final ManejadorActualizarItem manejadorActualizarItem;

    @Autowired
    public ComandoControladorItem(ManejadorCrearItem manejadorCrearItem,
                                  ManejadorEliminarItem manejadorEliminarItem,
                                  ManejadorActualizarItem manejadorActualizarItem) {
        this.manejadorCrearItem = manejadorCrearItem;
        this.manejadorEliminarItem = manejadorEliminarItem;
        this.manejadorActualizarItem = manejadorActualizarItem;
    }

    @PostMapping
    @ApiOperation("Crear Item")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoItem comandoItem) {
        return manejadorCrearItem.ejecutar(comandoItem);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Eliminar Item")
    public void eliminar(@PathVariable Long id) {
        manejadorEliminarItem.ejecutar(id);
    }

    @PutMapping
    @ApiOperation("Actualizar Item")
    public void actualizar(@RequestBody ComandoItem comandoItem) {
        manejadorActualizarItem.ejecutar(comandoItem);
    }
}
