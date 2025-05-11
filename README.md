# Sistema de Gestión de Tickets

## Información del Proyecto

**Universidad Nacional de Lanús**  
**Carrera:** Licenciatura en Sistemas  
**Materia:** Orientación a Objetos 2  
**Grupo:** N° 10

**Equipo Docente:**  
- Mg. Lic. María Alejandra Vranic  
- Esp. Lic. Gustavo Siciliano  
- Lic. Oscar Ruina  

**Integrantes:**  
- Alan Maciel
- Emiliano Ginarte Delgado
- Rodrigo Obando
- Santiago Zurlo

Este es un proyecto académico de consola desarrollado en **Java 8** utilizando **Hibernate 5** como ORM.  
El objetivo principal es simular un sistema de soporte mediante tickets, diferenciando roles entre clientes y empleados.

## Requisitos

- Java 8
- MySQL 8.0 o compatible
- Descargar las siguientes librerías manualmente desde el siguiente enlace:

  [Librerías del proyecto](https://drive.google.com/file/d/1lj66JwLiRMidE5xm6M3YH6Lfc9iVpiQl/view?usp=drive_link)

> El paquete contiene las dependencias provistas por la cátedra, junto con la librería `jbcrypt-0.4` utilizada para la autenticación de usuarios.
> Una vez descargadas, asegurarse de agregarlas al classpath del proyecto.

## Inicialización del Proyecto

Para comenzar, clonar este repositorio:

```bash
git clone https://github.com/rfnobando/hibernate-tickets.git
```

Antes de ejecutar el sistema, es necesario:

1. Crear la base de datos según el esquema proporcionado en `sql/init.sql`.  
   > **Advertencia:** Este script creará una base de datos llamada `hibernate_tickets` para el uso del sistema. Es recomendable verificar que no exista una base de datos con ese nombre en tu entorno antes de ejecutarlo.
  
2. Configurar el archivo de conexión a la base de datos copiando el archivo de ejemplo `app/src/hibernate.cfg.xml.example` a `app/src/hibernate.cfg.xml` y ajustarlo según el entorno.
   
3. Descargar las librerías requeridas y agregarlas al classpath del proyecto.

> **Nota:** Este proyecto no utiliza una estructura de IDE como Eclipse. Se puede compilar y ejecutar el proyecto de manera manual desde la consola o, si se prefiere, configurarlo en un IDE y compilarlo desde ahí.

## Funcionalidades

### Cliente

- ✅ Registrarse
- ✅ Iniciar sesión
- ✅ Crear ticket
- ✅ Ver tickets creados
- ✅ Ver mensajes de un ticket creado
- ✅ Responder un ticket creado

### Empleado

- ✅ Iniciar sesión
- ✅ Elegir un ticket sin asignar
- ✅ Ver tickets asignados
- ✅ Ver mensajes de un ticket asignado
- ✅ Responder un ticket asignado
- ✅ Cerrar un ticket asignado

> La persistencia del estado de sesión del usuario autenticado se maneja mediante una implementación interna, adecuada al contexto de una aplicación de consola.

## Ejecución

Para ejecutar el proyecto, se debe iniciar el método `main` de la clase `SystemTest`, ubicada en `app/src/test/`.
Desde dicha clase se pueden probar las distintas funcionalidades del sistema.
