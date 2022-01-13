package com.ceiba.venta.servicio;

import com.ceiba.venta.puerto.repositorio.RepositorioVenta;

public class ServicioEliminarVenta {

    private final RepositorioVenta repositorioVenta;

    public ServicioEliminarVenta(RepositorioVenta repositorioVenta) {
        this.repositorioVenta = repositorioVenta;
    }

    public void ejecutar(Long id) {
        this.repositorioVenta.eliminar(id);

    }

}
