# Content Calendar API

## Descripcion

La API de Content Calendar es una solución backend diseñada para gestionar un calendario de contenidos digitales.
Permite a los usuarios crear, actualizar, eliminar y consultar contenidos, los cuales pueden ser artículos, videos,
cursos o charlas de conferencias. Cada contenido se caracteriza por atributos como título, descripción, estado, tipo de
contenido, fechas de creación y actualización, y una URL opcional.

Esta API está construida utilizando Spring Boot, aprovechando sus capacidades para crear aplicaciones web robustas y
escalables de manera rápida. Además, utiliza PostgreSQL como sistema de gestión de bases de datos para almacenar y
gestionar los datos de los contenidos.

## Características

- CRUD de contenidos (Crear, Leer, Actualizar, Eliminar).
- Filtrado de contenidos por título y estado.
- Validación de datos de entrada.
- Integración con base de datos PostgreSQL.
- Monitorización y métricas con Spring Boot Actuator.

## Tecnologías Utilizadas

- Java
- Spring Boot
- Gradle
- PostgreSQL
- Docker

## Configuración y Usos

### Requisitos Previos

Para ejecutar y utilizar esta API, necesitarás tener instalado Docker y Docker Compose en tu sistema.

### Instrucciones de Uso

1. Clona el repositorio en tu máquina local.
2. Navega hasta la raíz del proyecto en tu terminal.
3. Ejecuta el siguiente comando para iniciar la base de datos PostgreSQL utilizando Docker Compose:

```bash
docker-compose -f ./docker-compose.yml up -d
```

Este comando levantará una instancia de PostgreSQL en un contenedor Docker, listo para ser utilizado por la aplicación.

4. Para detener y eliminar el contenedor de la base de datos, puedes utilizar el siguiente comando:

```bash
docker-compose -f ./docker-compose.yml down
```

5. Para ejecutar la aplicación, utiliza Gradle:

```bash
./gradlew bootRun
```

La API ahora estará accesible a través de `http://localhost:8080/content-calendar-api`.

### Monitorización con Actuator

La API de Content Calendar incluye Spring Boot Actuator para la monitorización y métricas. Actuator expone varios
endpoints que proporcionan información sobre el estado y el rendimiento de la aplicación.

- **Health Check**: Puedes verificar el estado de la aplicación accediendo
  a `http://localhost:8080/content-calendar-api/actuator/health`.

- **Información General**: Puedes obtener información general sobre la aplicación accediendo
  a `http://localhost:8080/content-calendar-api/actuator/info`.

Para ver todos los endpoints expuestos por Actuator, accede a `http://localhost:8080/content-calendar-api/actuator`.

**Nota:** Asegúrate de no incluir la barra final (`/`) en la URL cuando accedas a los endpoints de Actuator para evitar
errores 404.
