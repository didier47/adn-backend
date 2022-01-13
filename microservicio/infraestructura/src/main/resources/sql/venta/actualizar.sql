update venta
set idRepartidor = :idRepartidor,
    referencia   = :referencia,
    distancia    = :distancia,
    fechaEntrega = :fechaEntrega,
    valorEnvio   = :valorEnvio
where id = :id;