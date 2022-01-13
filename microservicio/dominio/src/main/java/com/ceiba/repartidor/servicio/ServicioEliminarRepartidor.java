package com.ceiba.repartidor.servicio;

import com.ceiba.dominio.excepcion.ExcepcionValorInvalido;
import com.ceiba.repartidor.puerto.repositorio.RepositorioRepartidor;

public class ServicioEliminarRepartidor {

    private static final String EL_REPARTIDOR_EXISTE_EN_UNA_VENTA = "El repartidor está asociado a una venta";

    private final RepositorioRepartidor repositorioRepartidor;

    public ServicioEliminarRepartidor(RepositorioRepartidor repositorioRepartidor) {
        this.repositorioRepartidor = repositorioRepartidor;
    }

    public void ejecutar(Long id) {
        validarExistenciaEnVenta(id);
        this.repositorioRepartidor.eliminar(id);

    }

    private void validarExistenciaEnVenta(Long id) {
        boolean existe = this.repositorioRepartidor.existeEnVenta(id);
        if (existe) {
            throw new ExcepcionValorInvalido(EL_REPARTIDOR_EXISTE_EN_UNA_VENTA);
        }
    }
}
