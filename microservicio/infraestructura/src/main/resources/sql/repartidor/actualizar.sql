update repartidor
set identificacion = :identificacion,
    nombres        = :nombres,
    apellidos      = :apellidos,
    telefono       = :telefono
where id = :id;