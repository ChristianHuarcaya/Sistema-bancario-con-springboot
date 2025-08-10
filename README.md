istema Bancario con Spring Boot - Pruebas Unitarias y API REST
Este proyecto es una aplicación backend desarrollada en Spring Boot 3, que simula funcionalidades básicas de un sistema bancario. Incluye manejo de cuentas, transferencias entre cuentas y control de transacciones a nivel de banco.

Características principales
CRUD completo para cuentas bancarias

Transferencias entre cuentas con validación de saldo

Registro y actualización del total de transferencias en bancos

Arquitectura limpia con capas de servicio y repositorio

Documentación automática de API con Swagger / OpenAPI

Pruebas unitarias con JUnit 5 y Mockito para garantizar la calidad del código

Base de datos en memoria H2 para pruebas y desarrollo rápido

Tecnologías utilizadas
Java 17

Spring Boot 3

Spring Data JPA

H2 Database

Springdoc OpenAPI

JUnit 5 & Mockito

Estructura del proyecto
controller: Controladores REST para manejo de endpoints

service: Lógica de negocio e implementación de servicios

repository: Acceso a datos con Spring Data JPA

entity: Entidades JPA que representan las tablas de la base de datos

dto: Objetos de transferencia de datos para operaciones específicas

exception: Manejo de excepciones personalizadas

tests: Pruebas unitarias con MockMvc, JUnit y Mockito





