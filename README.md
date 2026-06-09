# Price Service - Prueba Técnica

Este proyecto es un servicio Java que expone una API REST para obtener precios aplicables en función de una tarifa, identificador de producto y fecha de consulta.

## Requisitos

- Java 17 o superior instalado
- Maven instalado opcionalmente, aunque el proyecto incluye el wrapper de Maven (`mvnw` / `mvnw.cmd`)
- Conexión de red local para ejecutar el proyecto en la máquina donde está el código

## Cómo ejecutar el proyecto

Desde la raíz del proyecto, usa el wrapper de Maven:

```bash
./mvnw spring-boot:run
```

En Windows PowerShell o CMD:

```powershell
mvnw.cmd spring-boot:run
```

El servicio arranca en `http://localhost:8080`.

## Cómo probar el proyecto

Ejecuta las pruebas desde la raíz del proyecto con el wrapper de Maven:

```bash
./mvnw test
```

En Windows PowerShell o CMD:

```powershell
mvnw.cmd test
```

Esto ejecuta las pruebas automatizadas y muestra el resultado en la consola.

## API First

El contrato de la API está definido en `src/main/resources/openapi/price-api.yaml` usando OpenAPI 3.0.
Este fichero es la fuente de verdad de la API y se usa para generar la Swagger UI disponible en `/swagger-ui.html`.

El proyecto tiene configurado el `openapi-generator-maven-plugin`, que a partir del YAML es capaz 
de generar automáticamente ejecutando `mvn clean compile`:

- Interfaces de los controllers
- Modelos y DTOs
- Implementaciones vacías de los controllers
- Clientes HTTP en múltiples lenguajes (Java, TypeScript, Python...)
- Documentación estática

En este proyecto se ha configurado con `<interfaceOnly>true</interfaceOnly>` para mantener el código 
mínimo, ya que los modelos y el mapper están escritos a mano de forma más limpia. Una implementación 
API First completa haría que `PriceController` implementara la interfaz generada `PricesApi`, 
eliminando todas las anotaciones de mapping del controller y dejando que sea el contrato quien 
dirija la implementación. La infraestructura ya está preparada para habilitarlo con un solo cambio.

## Uso de IA

Durante el desarrollo de este proyecto se ha utilizado IA (Claude de Anthropic) como apoyo,
de forma similar a como se usaría cualquier otra herramienta de consulta como Stack Overflow
o la documentación oficial.

La IA se ha usado para:
- Resolver dudas puntuales sobre decisiones de diseño
- Validar que las decisiones tomadas seguían correctamente los principios de arquitectura hexagonal y SOLID
- Consultar compatibilidad de versiones entre dependencias
- Mejora de los tests para mayor cobertura