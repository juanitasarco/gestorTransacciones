# Banco RestApi
## Juan Sebastian Pardo Parra
## Juan Manuel Parrado Gomez
## Juanita Sariento Cortez
## Dependencias
- El proyecto hace uso de java 19.
- El archivo JSON se encuentra en la carpeta resources.
- El proyecto corre en el puerto 8080
- Los test corren en el puerto 8081


## Modelo de datos
| Account  |  |
| ------------- | ------------- |
| Id (PK)  | Int  |
| Type  | Varchar(100)  |
| Money  | Int  |
| date_created  | Date  |
| User_Document (FK)  | Int |

Many To One

| User  |  |
| ------------- | ------------- |
| Document (PK)  | Int  |
| Name  | Varchar(100)  |
| last_name  | Varchar(100)  |
| date_created  | Date  |


| Transaction  |  |
| ------------- | ------------- |
| Id (PK)  | Int  |
| Origen  | Int  |
| Destination  | Int  |
| Amount  | Int  |

## Dependencias del spring initializr

- **Spring Boot DevTools**: Proporciona reinicios rápidos de aplicaciones, LiveReload y configuraciones para mejorar la experiencia de desarrollo.
- **Spring Web**: Build web, including RESTful, applications using Spring MVC. Uses Apache Tomcat as the default embedded container.
- **Lombok**: Biblioteca de anotaciones de Java que ayuda a reducir el código repetitivo. (Getters y Setters)
- **H2 Database (SQL)**: Proporciona una base de datos en memoria rápida que admite la API de JDBC y el acceso R2DBC, con una huella pequeña (2 mb). Admite los modos integrado y de servidor, así como una aplicación de consola basada en navegador.
- **Spring Data JPA**: JPA es una especificación que indica cómo se debe realizar la persistencia (almacenamiento) de los objetos en programas Java. También una serie de anotaciones con las que podemos decorar nuestras clases, propiedades y métodos para indicar "mapeados".

## Estructura de capas
- **Controlador**: Comunicación con la web.
- **Servicios**: Logica de negocio.
- **Repositorio**: Interacción con el framework
- **Modelo**: Objeto Cuneta.

## Referencias
- **¿Como funciona JPA?**: https://www.campusmvp.es/recursos/post/la-api-de-persistencia-de-java-que-es-jpa-jpa-vs-hibernate-vs-eclipselink-vs-spring-jpa.aspx
- **Test with JPA and SpringBoot**: https://reflectoring.io/spring-boot-data-jpa-test/
