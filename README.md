# spring-basics-project

Proyecto de práctica para repasar los fundamentos de Spring Core y patrones de diseño en Java.

## Tecnologías

- Java 11
- Spring Context 5.3.10
- Maven
- JUnit 4

## Estructura del proyecto

```
src/main/java/com/curso/
├── config/
│   └── BeansConfiguration.java   # Configuración de beans con @Configuration
├── App.java                       # Punto de entrada, inicializa el ApplicationContext
├── RolesInterface.java            # Interfaz que deben implementar los roles
├── RolAdmin.java                  # Bean: rol administrador
├── RolNormal.java                 # Bean: rol normal
├── UsersInterface.java            # Interfaz de usuario
├── Users.java                     # Bean: usuario con rol asignado
├── Utils.java                     # Menú interactivo por consola
├── LRUCache.java                  # Implementación de caché LRU sobre LinkedHashMap
└── UserScene.java                 # Clase auxiliar (en desarrollo)
```

## Conceptos que cubre

### Spring Core — IoC y beans
- `AnnotationConfigApplicationContext` para arrancar el contexto sin XML.
- Clase `@Configuration` con métodos `@Bean` que definen los beans del contenedor.
- Inyección de dependencias por constructor: `Users` recibe un `RolesInterface` al crearse.
- Recuperación de beans por tipo y por nombre (`getBean`).

### Polimorfismo / patrón Strategy
- `RolesInterface` define el contrato (`saludo()`, `printRoleName()`).
- `RolAdmin` y `RolNormal` son implementaciones intercambiables.
- `Users` trabaja contra la interfaz, no contra implementaciones concretas.

### Estructura de datos
- `LRUCache<K, V>`: caché de capacidad fija con política *Least Recently Used*, implementada extendiendo `LinkedHashMap` con `accessOrder = true`.

### Aplicación de consola
- `Utils.processMenuOption` presenta un menú interactivo con opciones para leer datos de usuario o imprimirlos.

## Beans registrados

| Nombre bean | Tipo        | Descripción                             |
|-------------|-------------|-----------------------------------------|
| `normalRol` | `RolNormal` | Rol normal con nombre `"normal"`        |
| `adminRol`  | `RolAdmin`  | Rol administrador con nombre `"admin"`  |
| `users`     | `Users`     | Usuario por defecto con rol normal      |
| `adminUser` | `Users`     | Usuario administrador con rol admin     |

## Cómo ejecutar

```bash
mvn compile exec:java -Dexec.mainClass="com.curso.App"
```

O desde el IDE ejecutando `App.main()`.