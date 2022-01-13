package com.ceiba.repartidor.controlador;

import com.ceiba.repartidor.consulta.ManejadorListarRepartidores;
import com.ceiba.repartidor.modelo.dto.DtoRepartidor;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/repartidores")
@Api(tags = {"Controlador consulta repartidor"})
public class ConsultaControladorRepartidor {

    private final ManejadorListarRepartidores manejadorListarRepartidores;

    public ConsultaControladorRepartidor(ManejadorListarRepartidores manejadorListarRepartidores) {
        this.manejadorListarRepartidores = manejadorListarRepartidores;
    }

    @GetMapping
    @ApiOperation("Listar Repartidores")
    public List<DtoRepartidor> listar() {
        return this.manejadorListarRepartidores.ejecutar();
    }

}
