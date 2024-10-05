# Employee Technical Test

## Descripción

Este proyecto es una API de gestión de empleados que utiliza Spring Boot y QueryDSL para interactuar con una base de
datos. Permite realizar operaciones CRUD sobre los empleados.

## Requisitos Previos

Asegúrate de tener instalados los siguientes requisitos en tu máquina:

- [Java 17](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven](https://maven.apache.org/download.cgi)
- [Base de datos MySQL](https://www.mysql.com/downloads/)

## Instalación

Sigue estos pasos para instalar y configurar el proyecto:

1. **Clonar el repositorio:**

   ```bash
   git clone https://github.com/riquelmip/employees-technical-test.git
   ```


2. **Configurar la base de datos:**

    - Crea una base de datos en MySQL.
    - Abre el archivo `src/main/resources/application.properties` y configura las
      propiedades `spring.datasource.url`, `spring.datasource.username` y `spring.datasource.password` con los datos de
      tu base de datos.


3. **Limpiar el proyecto:**

   ```bash
   mvn clean
   ```

4. **Compilar el proyecto:**

   ```bash 
    mvn compile
    ```

5. **Ejecutar el proyecto:**

    ```bash
    mvn spring-boot:run
    ```

6. **Acceder a la API:**

   Abre un navegador y accede a la URL `http://localhost:8080/api.
   La documentación de la API se encuentra en la URL `[https://documenter.getpostman.com/view/27314058/2sAXxMfteU](https://documenter.getpostman.com/view/27314058/2sAXxMfteU)`.
