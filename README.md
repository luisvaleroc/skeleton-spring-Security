# Mi Aplicación Básica en Spring Boot con Roles de Usuarios y MySQL

¡Bienvenido a mi aplicación básica en Spring Boot con roles de usuarios y MySQL! Esta es una aplicación de ejemplo que muestra cómo iniciar un proyecto simple utilizando Spring Boot, con la inclusión de roles de usuarios y una base de datos MySQL como características iniciales.

## Descripción

Esta aplicación básica de Spring Boot proporciona un punto de partida simple para construir aplicaciones web que requieren autenticación y autorización de usuarios. Incluye configuraciones mínimas y un ejemplo de controlador REST para demostrar cómo crear un punto de acceso básico, así como también la configuración inicial para gestionar roles de usuarios y conectar la aplicación a una base de datos MySQL.

## Requisitos

- Java Development Kit (JDK) 17
- Maven 3.2 o superior (si prefieres Gradle, puedes usarlo en su lugar)
- MySQL Server

## Configuración de la Base de Datos

1. Asegúrate de tener MySQL Server instalado y en funcionamiento en tu sistema.

2. Crea una base de datos llamada `nombre_de_la_base_de_datos`.

3. En el archivo `src/main/resources/application.properties`, configura las propiedades de conexión a tu base de datos MySQL:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/nombre_de_la_base_de_datos?useSSL=false&serverTimezone=UTC
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update
    ```

   Reemplaza `nombre_de_la_base_de_datos`, `tu_usuario` y `tu_contraseña` con la información correspondiente a tu base de datos MySQL.

## Ejecución

Para ejecutar la aplicación, sigue estos pasos:

1. Clona este repositorio en tu máquina local.

    ```shell
    git clone https://url_del_repositorio.git
    ```

2. Navega al directorio de la aplicación.

    ```shell
    cd nombre_de_la_aplicacion
    ```

3. Ejecuta la aplicación usando Maven.

    ```shell
    mvn spring-boot:run
    ```

4. Abre un navegador web y visita `http://localhost:8080` para ver la aplicación en funcionamiento.

## Estructura del Proyecto

- `src/main/java`: Contiene los archivos fuente de la aplicación.
- `src/main/resources`: Contiene los archivos de configuración y recursos estáticos.
- `pom.xml`: Archivo de configuración de Maven que especifica las dependencias del proyecto.

## Contribución

¡Las contribuciones son bienvenidas! Si encuentras algún problema o tienes alguna mejora, por favor crea un pull request o abre un issue en este repositorio.

## Contacto

Si tienes alguna pregunta o comentario, no dudes en ponerte en contacto conmigo a través de [correo electrónico](mailto:tu_correo@example.com) o [Twitter](https://twitter.com/tu_usuario).

¡Gracias por usar mi aplicación básica en Spring Boot con roles de usuarios y MySQL!
