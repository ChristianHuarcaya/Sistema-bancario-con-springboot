Sistema Bancario - Spring Boot 3 + Pruebas Unitarias + API REST


Sistema backend que simula funciones básicas bancarias con gestión de cuentas, transferencias y control de transacciones a nivel de banco.

✅ Funcionalidades principales:
Listar cuentas bancarias

- Obtener detalles de una cuenta por ID

- Crear nueva cuenta bancaria

- Consultar total de transferencias realizadas en un banco

- Consultar saldo de una cuenta

- Transferencias entre cuentas con validación de saldo y actualización del total de transferencias del banco

- Manejo de excepciones para saldo insuficiente

🚀 Tecnologías utilizadas:
Java 17

Spring Boot 3

Spring Data JPA

H2 Database (base de datos en memoria para pruebas)

Springdoc OpenAPI (Swagger)

JUnit 5 + Mockito para pruebas unitarias


## 📂Estructura de paquetes:

controller — Endpoints REST

service — Lógica de negocio

repository — Acceso a datos con JPA

entity — Entidades JPA (Cuenta, Banco)

dto — Objetos para transferencia de datos (TransaccionDTO)

exception — Excepciones personalizadas (DineroInsuficienteException)

tests — Pruebas unitarias con MockMvc, JUnit y Mockito

🚀 Para ejecutarlo:
Clona el proyecto

Ejecuta la aplicación principal

Accede a la documentación Swagger en /swagger-ui.html (o el path configurado)

Prueba los endpoints para gestionar cuentas y realizar transferencias

👨‍💻 Autor
Cristian Huarcaya Pumahualcca
Desarrollador Backend en Java
LinkedIn | GitHub







