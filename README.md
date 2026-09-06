# API de Acceso y Manipulación de Datos en Spring Boot 🚀

Este repositorio contiene un proyecto desarrollado en la asignatura de **Desarrollo Web en Entorno Servidor (DWES)** de 2º de DAW. El objetivo principal es gestionar la persistencia y lógica de negocio para entidades como Empleados, Departamentos, Libros, Usuarios y Vuelos.

## 🛠️ Tecnologías Utilizadas
* **Backend:** Java con Spring Boot
* **Persistencia:** Spring Data JPA / Hibernate
* **Base de Datos:** MySQL / H2 (configurada mediante `data.sql` y `application.properties`)
* **Gestor de Dependencias:** Maven (`pom.xml`)

## 🏗️ Arquitectura del Proyecto
El proyecto sigue el patrón de diseño por capas estándar en la industria:
1. **Controllers:** Exposición de endpoints y manejo de peticiones HTTP.
2. **Services:** Lógica de negocio de la aplicación.
3. **Repositories:** Interfaces de Spring Data para la comunicación directa con la base de datos.

## 📋 Requisitos para Ejecutar
* Java JDK 17 o superior.
* Un entorno de desarrollo como IntelliJ IDEA o Eclipse.
* Maven integrado.
