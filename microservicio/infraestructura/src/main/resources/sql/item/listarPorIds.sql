select id, referencia, nombre, cantidad
from item
where id in (:ids);