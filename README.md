---

# LiterAluraProyect

## Descripción del Proyecto

LiterAluraProyect es una aplicación Java diseñada para consumir y procesar datos de una API externa sobre autores y libros, transformándolos en un formato adecuado para su visualización y gestión.

## Breve Explicación

LiterAluraProyect es una herramienta diseñada para interactuar con una API que proporciona información sobre autores y libros. La aplicación procesa estos datos y los almacena en una base de datos PostgreSQL, permitiendo una fácil gestión y visualización. Utiliza Spring Boot para la infraestructura del backend y Jackson para el manejo de JSON.

## Requisitos del Sistema

- Java 17
- Maven 3.6.0 o superior
- PostgreSQL

## Instalación

1. Clona el repositorio:
    ```bash
    git clone https://github.com/angelaramiz/LiterAluraProyect.git
    ```
2. Navega al directorio del proyecto:
    ```bash
    cd LiterAluraProyect
    ```
3. Configura la base de datos PostgreSQL y actualiza el archivo `application.properties` con tus credenciales.
4. Compila el proyecto usando Maven:
    ```bash
    mvn clean install
    ```

## Ejecución

Para ejecutar la aplicación, usa el siguiente comando:
```bash
java -jar target/LiterAluraProyect-0.0.1-SNAPSHOT.jar
```

## Dependencias

El archivo `pom.xml` define las siguientes dependencias principales:

- `spring-boot-starter-data-jpa`: Starter para Spring Data JPA.
- `postgresql`: Driver de PostgreSQL.
- `spring-boot-starter-test`: Starter para pruebas en Spring Boot.
- `jackson-databind`: Biblioteca para trabajar con JSON.

## Funcionalidades

- **Consumo de API**: La aplicación realiza peticiones a una API externa para obtener datos sobre autores y libros.
- **Conversión de Datos**: Los datos obtenidos se convierten a un formato adecuado para su visualización.
- **Visualización**: Los datos procesados se muestran de manera estructurada.
- **Gestión de Datos**: Uso de repositorios para gestionar la persistencia de datos de autores y libros.

## Configuración de la Base de Datos

Para configurar la base de datos, asegúrate de tener PostgreSQL instalado y en funcionamiento. Actualiza el archivo `application.properties` con tus credenciales de base de datos.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/nombre_de_tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

---
