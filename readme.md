Este bloque contiene la estructura necesaria para construir un proyecto en java favoreciendo el enfoque de DDD.

Los principales patrones y estilos de arquitectura que guían este bloque son

#### Arquitectura hexagonal
Arquitectura que fomenta  que nuestro dominio sea el núcleo de todas las capas, también conocida como puertos y adaptadores en la cual el dominio define los puertos y en las capas superiores se definen los adaptadores para desacoplar el dominio. Se divide princialmente en tres capas, **aplicación**, **dominio** e **infraestructura**
- **Infraestructura**: Capa que tiene las responsabilidades de realizar los adaptadores a los puertos definidos en el domino, exponer web services, consumir web services, realizar conexiones a bases de datos, ejecutar sentencias DML, en general todo lo que sea implementaciones de cualquier framework
- **Aplicación**: capa encargada de enrutar los eventos entrantes de la capa de infraestructura hacía la capa del dominio, generalmente se conoce como una barrera transaccional la cual agrupa toda la invocación de un caso de uso, se pueden encontrar patrones como Fabricas, Manejadores de Comandos, Bus de eventos, etc
- **Dominio**: representa toda la lógica de negocio de la aplicación la cual es la razón de existir del negocio. Se busca evitar el anti-patron [https://martinfowler.com/bliki/AnemicDomainModel.html](https://martinfowler.com/bliki/AnemicDomainModel.html)  y favorecer el principio [https://martinfowler.com/bliki/TellDontAsk.html](https://martinfowler.com/bliki/TellDontAsk.html) en esta capa se pueden encontrar los siguientes patrones agregados, servicios de dominio, entidades, objetos de valor, repositorios (puerto), etc.

Para obtener mas documentación sobre este tipo de arquitectura se recomienda [https://codely.tv/blog/screencasts/arquitectura-hexagonal-ddd/](https://codely.tv/blog/screencasts/arquitectura-hexagonal-ddd/)

#### Patrón CQRS:
Patrón con el cual dividimos nuestro modelo de objetos en dos, un modelo para consulta y un modelo para comando (modificación de datos). Este patrón es recomendado cuando se va desarrollar lógica de negocio compleja porque nos ayuda a separar las responsabilidades y a mantener un modelo de negocio consistente.

- **Consulta**: modelo a través del cual se divide la responsabilidad para presentar datos en la interfaz de usuario, los objetos se modelan basado en lo que se va a presentar y no en la lógica de negocio, ejm: ver facturas, consultar clientes
- **Comando**: son todas las operaciones que cambian el estado del sistema, ejm: (facturar, aplicar descuento), este modelo se construye todo el modelo de objetos basado en la lógica de negocio de la aplicación

Para mayor documentación del patrón [https://martinfowler.com/bliki/CQRS.html](https://martinfowler.com/bliki/CQRS.html)

#### Especificaciones técnicas:

- Spring boot 2.5.2
- Flayway -> administrar todos los script DDL e iniciliazadores de la bd
- Integración con Jenkins: Jenkins File
- Integración con Sonar: Sonar properties
- Acceso a la base de datos por medio de JDBC template
- Se entregan pruebas de muestra automatizadas para cada una de las capas
- Pruebas de carga de ejemplo en el directorio microservicio/external-resources
- Ejemplo de como modelar un Comando y un Query
- Ejemplo de pruebas de integración con H2
- Java 8
- Se debe tener configurado el IDE con Lombok, descargar desde (https://projectlombok.org/download)

#### Tener en cuenta para usar bloque para producción:
Al momento de descargar este bloque y se vaya usar de manera productiva se debe ejecutar la tarea de gradle **dependencyCheckAnalyze** la cual revisa las vulnerabilidades para las versiones de las librerias que usa el bloque. El reporte que genera esta tarea se encuentra en la carpeta build del proyecto raiz. Se deben resolver los reportes críticos

#### Tener en cuenta cada que se incluya una nueva libreria:
Despues de incluir una libreria en gradle se debe ejecutar la tarea de gradle **dependencyCheckAnalyze** para revisar las vulnerabilidades reportadas.

#### Estructura del proyecto:
Se identifican dos carpetas principales, común y microservicio. Microservicio es la carpeta que contiene todo el código fuente para el primer microservicio de su proyecto, se recomienda cambiar el nombre de esta carpeta por la de su lógica de negocio y posteriormente modificar el archivo *settings.gradle*,  si necesita crear mas microservicios lo único que debe realizar es duplicar esta carpeta y realizar la modificación en el archivo *settings.gradle*. El proyecto común contiene código fuente que comparten todos los microservicios y capas, es una librería que importan los que requieran este código compartido, es importante tener en cuenta que no debe ir código de negocio en este lugar.
Como cada microservicio se va realizar con CQRS, cada uno contiene su propia estructura de arquitectura hexagonal en la cual no se comparten los modelos.

#### Importar el proyecto:
Para importar el proyecto se recomienda usar Gradle en la versión 5.0, se debe importar desde la ruta *microservicio/build.gradle*
Después de importar el proyecto se queda viendo de la siguiente manera

![enter image description here](https://drive.google.com/uc?id=1x2ZVpM2steX0Er-jDNoffQ_V6pRVdW0k)

#### Bloque HealthCheck
Es un bloque que tiene como fin saber el estado de otros bloques o servicios agregados como por ejemplo de mysql,sqlServer etc.Para esto es necesario crear un paquete com.ceiba.core.actuator en microservicio-consulta-infraestructura    e implementar una interfaz llamada  HealthCheck  con la anotacion @Component sobreescribiendo los siguientes metodos:

- **registrarBloque()**: Que tiene la funcion de registrar el bloque que quiere tenerse encuenta ,para esto es necesario que ala hora de construir la clase que implemente  la interface HealthCheck se inyecte la clase manejadorHealthCheckBloques que tiene en memoria los bloques
  implementados pasandole la cadena del nombre y la clase en si.

- **healthCheck()**: Es un metodo que se le delega al programador para que segun el servicio o el bloque usado implemente una funcionalidad que logre detectar que este ya no esta arriba.No devuleve un valor si no excepcion de tipo ExepcionBloqueSinServicio.

Al momento de crear el bloque principal pedira un tiempo que estara dado en  milisegundos llamado tiempoHealthCheck que estara guardado en archivo application.yaml de resources del microservicio.


nota* Es recomendable  tener muy encuenta el tiempo asignado a HealthCheck como tal en las base de datos el tiempo que tarda en verificar es 30000 milisegundos que en segundos son 30 entoces debe ser mayor a este , para que cuando  healthCheck  realice la revision ,ya todos los bloques hallan devuelto su valor para no tener  inconsistencias de
los valores devuelto .Se esperaria aumentar el tiempo cada vez que un bloque se implemente dependiendo tambien de su tiempo de retardo.



*Cualquier duda o aporte con este bloque contactar a juan.botero@ceiba.com.co o juan.castano@ceiba.com.co*

Hash de git relacionado: 44dd4ccd

#### Jenkinsfile-Sonar

Este bloque contiene los archivos de configuración necesarios para un ambiente de integración continua.

Los archivos de configuración que se encuentran en este bloque son:

#### Jenkinsfile
Dentro de este archivo se encuentra un Job (Tipo Pipeline). Este job permite definir el ciclo de vida completo de la aplicación (descargar código, compilar, test, publicar, desplegar, etc.) mediante código. De esta forma, resulta mucho más sencillo replicar los diferentes pasos con distintas aplicaciones y gestionar mejor los cambios en cada paso. Se puede visualizar en un solo job cada paso del ciclo de vida, lo que anteriormente se configuraba con varios jobs de estilo libre encadenados y visibles en un Build Pipeline.

#### Jenkinsfile (Pipeline Declarativo)
Un pipeline declarativo es aquel que podemos crear, editar o configurar mediante una estructura definida que nos ayuda a entender y predecir los pasos del pipeline.

#### Sintaxis básica de un Pipeline Declarativo

- **Pipeline {}**: Identificamos dónde empieza y termina el pipeline así como los pasos que tiene.
- **Agent**: Especificamos cuando se ejecuta el pipeline. Uno de los comandos más utilizados es any, para ejecutar el pipeline siempre y cuando haya un ejecutor libre en Jenkins.
- **Stages**: Bloque donde se definen una serie de estados a realizar dentro del pipeline. 
- **Stage**: Bloque que define una serie de tareas realizadas dentro del pipeline, por ejemplo: Build. test, deploy, etc. Podemos utilizar varios plugins en Jenkins para visualizar el estado o el progreso de estos estados
- **Steps**: Son todos los pasos a realizar dentro de un stage. Podemos definir uno o varios pasos.
- **Step**:  Es una tarea simple dentro del pipeline. Fundamentalmente es un paso donde se le dice a Jenkins qué hacer en un momento específico o paso del proceso. Por ejemplo, para ejecutar un comando en shell podemos tener un paso en el que tengamos la línea sh “ls” para mostrar el listado de ficheros de una carpeta. 

![enter image description here] https://drive.google.com/file/d/1fsO6rVax0tAWmbTil4fRY24bpw3e8GsM/view?usp=sharing

#### Requerimientos: 

 - Proyecto Gradle (Como mínimo la estructura del Proyecto)
 - Repositorio en GitHub con el contenido del Proyecto
 - Correo electrónico para reportar un fallo de jenkins
 - Los archivos Jenkinsfile y sonar-project.properties deben ser guardados en la raíz del proyecto


#### sonar-project

sonar-project.properties contiene las propiedades de análisis. Con este archivo se simplifica la línea que ejecuta del SonarScanner dado que la instrucción no tendrá “quemadas” las propiedades y al estar en la raíz del proyecto hace que no sea necesario especificar la ruta y nombre de éste. Siendo así, el sonar scanner “leerá” el archivo .properties y posteriormente mandará el análisis al SonarQube Server configurado e identificado en Jenkins con ​withSonarQubeEnv(​'Sonar'​).
 
Hash de git relacionado: 44dd4ccd