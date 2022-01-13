package com.ceiba.item.controlador;

import com.ceiba.item.consulta.ManejadorListarItems;
import com.ceiba.item.modelo.dto.DtoItem;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/items")
@Api(tags = {"Controlador consulta item"})
public class ConsultaControladorItem {

    private final ManejadorListarItems manejadorListarItems;

    public ConsultaControladorItem(ManejadorListarItems manejadorListarItems) {
        this.manejadorListarItems = manejadorListarItems;
    }

    @GetMapping
    @ApiOperation("Listar Items")
    public List<DtoItem> listar() {
        return this.manejadorListarItems.ejecutar();
    }

}
