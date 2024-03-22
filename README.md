Configuración Proyecto
========================

Para ejecutar el proyecto de forma adecuada, se deben tener en cuenta los siguientes puntos:

1. Tener Java 21 configurado e instalado: https://www.oracle.com/java/technologies/downloads/#jdk21-mac
2. Tener MySql 8.x.x configurado e instalado: https://dev.mysql.com/downloads/mysql/

## Configuración conexión a base de datos

La conexón con la base de datos se debe realizar en el archivo **application.yml**. En este archivo se debe especificar el **host**, **usuario** y **password** de la base de datos a la que nos queremos conectar, como se inidica en el ejemplo:

```yaml
spring:
  datasource:
    url: jdbc:mysql:/{HOST}:{PORT}/{DB_NAME}
    username: {USERNAME}
    password: {PASSWORD}
    driver-class-name: com.mysql.cj.jdbc.Driver
```
>**Nota:** Reemplazar los datos encerrados en *{}* por sus respectivos valores.

## Ejecutar el proyecto

Una vez todo lo anterior este configurado, se puede ejecutar el proyecto desde el archivo **SolicitudesApplication.java** o desde el botón *run* ubicado en la parte superior derecha de Intellij.
