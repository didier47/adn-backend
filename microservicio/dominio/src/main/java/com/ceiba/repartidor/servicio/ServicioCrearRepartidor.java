package com.ceiba.repartidor.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.repartidor.modelo.entidad.Repartidor;
import com.ceiba.repartidor.puerto.repositorio.RepositorioRepartidor;


public class ServicioCrearRepartidor {

    private static final String EL_REPARTIDOR_YA_EXISTE_EN_EL_SISTEMA = "El repartidor ya existe en el sistema";

    private final RepositorioRepartidor repositorioRepartidor;

    public ServicioCrearRepartidor(RepositorioRepartidor repositorioRepartidor) {
        this.repositorioRepartidor = repositorioRepartidor;
    }

    public Long ejecutar(Repartidor repartidor) {
        validarExistenciaPrevia(repartidor);
        return this.repositorioRepartidor.crear(repartidor);
    }

    private void validarExistenciaPrevia(Repartidor repartidor) {
        boolean existe = this.repositorioRepartidor.existe(repartidor.getIdentificacion());
        if (existe) {
            throw new ExcepcionDuplicidad(EL_REPARTIDOR_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
