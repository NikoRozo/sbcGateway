# Proyecto Spring Cloud Gateway

## Descripción

Este proyecto implementa un servicio de API Gateway utilizando Spring Cloud Gateway, que actúa como punto de entrada centralizado para las solicitudes API, gestionando el enrutamiento de peticiones hacia los microservicios correspondientes.

## Tecnologías

- Java
- Spring Boot
- Spring Cloud Gateway
- Netflix Eureka Client
- Maven

## Configuración

### Propiedades Principales

- Nombre de la aplicación: sbcGateway
- Puerto: 8090
- Registro en Eureka: http://localhost:8761/eureka/

### Rutas Configuradas

#### Microservicio de Productos

- ID: sbcProducts
- URI: lb://sbcProducts (Load Balanced)
- Ruta: /api/products/**
- Predicados:
    - Header requerido: token=123456
    - Métodos permitidos: GET, POST
    - Query param: color=verde
    - Cookie: color=red
- Filtros:
    - StripPrefix=2 (elimina los primeros 2 segmentos de la URL)
    - SampleCookie (añade cookie personalizada)

#### Microservicio de Items

- ID: sbcItems
- URI: lb://sbcItems (Load Balanced)
- Ruta: /api/items/**
- Filtros:
    - StripPrefix=2
    - Adición de cabeceras de solicitud y respuesta
    - Adición de parámetros de solicitud
    - Configuración de cookies personalizadas
    - Establecimiento del Content-Type de la respuesta

## Filtros Personalizados

### SampleCookieGatewayFilterFactory

Implementa un filtro personalizado que añade cookies a la respuesta HTTP.

Configuración del filtro:
- name: Nombre de la cookie
- value: Valor de la cookie
- message: Mensaje personalizado (uso interno)

## Ejecución

Para ejecutar el proyecto:

```bash
mvn spring-boot:run
```

## Requisitos

- Java 8 o superior
- Maven 3.6.x o superior
- Servicio de registro Eureka en ejecución

## Estructura del Proyecto

- `application.properties`: Configuración básica de la aplicación
- `application.yml`: Configuración de rutas y filtros de Gateway
- `SampleCookieGatewayFilterFactory.java`: Implementación del filtro personalizado para cookies# sbcGateway
