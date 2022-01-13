package com.ceiba.repartidor.consulta;

import com.ceiba.repartidor.modelo.dto.DtoRepartidor;
import com.ceiba.repartidor.puerto.dao.DaoRepartidor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ManejadorListarRepartidores {

    private final DaoRepartidor daoRepartidor;

    public ManejadorListarRepartidores(DaoRepartidor daoRepartidor) {
        this.daoRepartidor = daoRepartidor;
    }

    public List<DtoRepartidor> ejecutar() {
        return this.daoRepartidor.listar();
    }
}
