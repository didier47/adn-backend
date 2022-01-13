package com.ceiba.venta.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;


public class ServicioCrearVenta {

    private static final String LA_VENTA_YA_EXISTE_EN_EL_SISTEMA = "La venta ya existe en el sistema";

    private final RepositorioVenta repositorioVenta;

    public ServicioCrearVenta(RepositorioVenta repositorioVenta) {
        this.repositorioVenta = repositorioVenta;
    }

    public Long ejecutar(Venta venta) {
        validarExistenciaPrevia(venta);
        return this.repositorioVenta.crear(venta);
    }

    private void validarExistenciaPrevia(Venta venta) {
        boolean existe = this.repositorioVenta.existe(venta.getReferencia());
        if (existe) {
            throw new ExcepcionDuplicidad(LA_VENTA_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
