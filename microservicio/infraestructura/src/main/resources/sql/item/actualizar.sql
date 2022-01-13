update item
set referencia = :referencia,
    nombre     = :nombre,
    cantidad   = :cantidad
where id = :id;