package com.ceiba.venta.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.venta.comando.ComandoVenta;
import com.ceiba.venta.comando.manejador.ManejadorActualizarVenta;
import com.ceiba.venta.comando.manejador.ManejadorCrearVenta;
import com.ceiba.venta.comando.manejador.ManejadorEliminarVenta;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ventas")
@Api(tags = {"Controlador comando venta"})
public class ComandoControladorVenta {

    private final ManejadorCrearVenta manejadorCrearVenta;
    private final ManejadorEliminarVenta manejadorEliminarVenta;
    private final ManejadorActualizarVenta manejadorActualizarVenta;

    @Autowired
    public ComandoControladorVenta(ManejadorCrearVenta manejadorCrearVenta,
                                   ManejadorEliminarVenta manejadorEliminarVenta,
                                   ManejadorActualizarVenta manejadorActualizarVenta) {
        this.manejadorCrearVenta = manejadorCrearVenta;
        this.manejadorEliminarVenta = manejadorEliminarVenta;
        this.manejadorActualizarVenta = manejadorActualizarVenta;
    }

    @PostMapping
    @ApiOperation("Crear Venta")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoVenta comandoVenta) {
        return manejadorCrearVenta.ejecutar(comandoVenta);
    }

    @DeleteMapping(value = "/{id}")
    @ApiOperation("Eliminar Venta")
    public void eliminar(@PathVariable Long id) {
        manejadorEliminarVenta.ejecutar(id);
    }

    @PutMapping
    @ApiOperation("Actualizar Venta")
    public void actualizar(@RequestBody ComandoVenta comandoVenta) {
        manejadorActualizarVenta.ejecutar(comandoVenta);
    }
}
