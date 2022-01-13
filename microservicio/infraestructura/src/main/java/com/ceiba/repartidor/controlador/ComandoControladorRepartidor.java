package com.ceiba.repartidor.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.repartidor.comando.ComandoRepartidor;
import com.ceiba.repartidor.comando.manejador.ManejadorActualizarRepartidor;
import com.ceiba.repartidor.comando.manejador.ManejadorCrearRepartidor;
import com.ceiba.repartidor.comando.manejador.ManejadorEliminarRepartidor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/repartidores")
@Api(tags = {"Controlador comando repartidor"})
public class ComandoControladorRepartidor {

    private final ManejadorCrearRepartidor manejadorCrearRepartidor;
    private final ManejadorEliminarRepartidor manejadorEliminarRepartidor;
    private final ManejadorActualizarRepartidor manejadorActualizarRepartidor;

    @Autowired
    public ComandoControladorRepartidor(ManejadorCrearRepartidor manejadorCrearRepartidor,
                                        ManejadorEliminarRepartidor manejadorEliminarRepartidor,
                                        ManejadorActualizarRepartidor manejadorActualizarRepartidor) {
        this.manejadorCrearRepartidor = manejadorCrearRepartidor;
        this.manejadorEliminarRepartidor = manejadorEliminarRepartidor;
        this.manejadorActualizarRepartidor = manejadorActualizarRepartidor;
    }

    @PostMapping
    @ApiOperation("Crear Repartidor")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoRepartidor comandoRepartidor) {
        return manejadorCrearRepartidor.ejecutar(comandoRepartidor);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Eliminar Repartidor")
    public void eliminar(@PathVariable Long id) {
        manejadorEliminarRepartidor.ejecutar(id);
    }

    @PutMapping
    @ApiOperation("Actualizar Repartidor")
    public void actualizar(@RequestBody ComandoRepartidor comandoRepartidor) {
        manejadorActualizarRepartidor.ejecutar(comandoRepartidor);
    }
}
