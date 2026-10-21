# Dinobox - Backend

Backend del proyecto Dinobox, desarrollado para la asignatura Desarrollo Fullstack II.

Se utilizó Spring Boot, Java, MySQL y Spring Data JPA.

## Funcionalidades

- Registrar encomiendas con código de seguimiento.
- Consultar y listar encomiendas.
- Actualizar el estado de una encomienda.
- Eliminar encomiendas.

## Ejecución

1. Crear una base de datos llamada `dinobox` en MySQL.
2. Configurar el usuario y contraseña de MySQL en `application.properties` (la contraseña se obtiene de `DB_PASSWORD`).
3. Ejecutar `DinoboxApplication.java` desde VS Code.

El backend funciona en `http://localhost:8080`.

La documentación de la API se puede revisar en `http://localhost:8080/swagger-ui/index.html`.
