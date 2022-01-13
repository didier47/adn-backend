package com.ceiba.venta.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.venta.modelo.entidad.Venta;
import com.ceiba.venta.puerto.repositorio.RepositorioVenta;

public class ServicioActualizarVenta {

    private static final String LA_VENTA_NO_EXISTE_EN_EL_SISTEMA = "La venta no existe en el sistema";

    private final RepositorioVenta repositorioVenta;

    public ServicioActualizarVenta(RepositorioVenta repositorioVenta) {
        this.repositorioVenta = repositorioVenta;
    }

    public void ejecutar(Venta venta) {
        validarExistenciaPrevia(venta);
        this.repositorioVenta.actualizar(venta);
    }

    private void validarExistenciaPrevia(Venta venta) {
        boolean existe = this.repositorioVenta.existePorId(venta.getId());
        if (!existe) {
            throw new ExcepcionDuplicidad(LA_VENTA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
