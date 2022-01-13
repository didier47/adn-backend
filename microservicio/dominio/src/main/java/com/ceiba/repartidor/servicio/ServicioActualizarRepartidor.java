package com.ceiba.repartidor.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.repartidor.modelo.entidad.Repartidor;
import com.ceiba.repartidor.puerto.repositorio.RepositorioRepartidor;

public class ServicioActualizarRepartidor {

    private static final String EL_REPARTIDOR_NO_EXISTE_EN_EL_SISTEMA = "El repartidor no existe en el sistema";

    private final RepositorioRepartidor repositorioRepartidor;

    public ServicioActualizarRepartidor(RepositorioRepartidor repositorioRepartidor) {
        this.repositorioRepartidor = repositorioRepartidor;
    }

    public void ejecutar(Repartidor repartidor) {
        validarExistenciaPrevia(repartidor);
        this.repositorioRepartidor.actualizar(repartidor);
    }

    private void validarExistenciaPrevia(Repartidor repartidor) {
        boolean existe = this.repositorioRepartidor.existePorId(repartidor.getId());
        if (!existe) {
            throw new ExcepcionDuplicidad(EL_REPARTIDOR_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
