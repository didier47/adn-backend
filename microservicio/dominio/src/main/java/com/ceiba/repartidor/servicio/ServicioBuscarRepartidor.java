package com.ceiba.repartidor.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.repartidor.modelo.dto.DtoRepartidor;
import com.ceiba.repartidor.puerto.dao.DaoRepartidor;
import com.ceiba.repartidor.puerto.repositorio.RepositorioRepartidor;

public class ServicioBuscarRepartidor {

    private static final String EL_REPARTIDOR_NO_EXISTE_EN_EL_SISTEMA = "El repartidor no existe en el sistema";

    private final DaoRepartidor daoRepartidor;
    private final RepositorioRepartidor repositorioRepartidor;

    public ServicioBuscarRepartidor(DaoRepartidor daoRepartidor, RepositorioRepartidor repositorioRepartidor) {
        this.daoRepartidor = daoRepartidor;
        this.repositorioRepartidor = repositorioRepartidor;
    }

    public DtoRepartidor ejecutar(Long id) {
        validarExistenciaPrevia(id);
        return this.daoRepartidor.buscarPorId(id);
    }

    private void validarExistenciaPrevia(Long id) {
        boolean existe = this.repositorioRepartidor.existePorId(id);
        if (!existe) {
            throw new ExcepcionDuplicidad(EL_REPARTIDOR_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
