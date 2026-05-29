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
│   └── BeansConfiguration.java     # Configuración de beans con @Configuration y @Value
├── App.java                        # Punto de entrada, inicializa el ApplicationContext
├── RolesInterface.java             # Contrato que deben implementar los roles
├── RolType.java                    # Enum de tipos de rol: ROL_NORMAL, ROL_ADMIN
├── RolAdmin.java                   # Bean: rol administrador
├── RolNormal.java                  # Bean: rol normal
├── UsersInterface.java             # Contrato que deben implementar los usuarios
├── User.java                       # Entidad usuario con rol asignado
├── UserService.java                # Servicio: escribe usuarios en la caché LRU
├── Utils.java                      # Scanner compartido y menú interactivo por consola
└── LRUCache.java                   # Implementación de caché LRU sobre LinkedHashMap
```

## Conceptos que cubre

### Spring Core — IoC y beans
- `AnnotationConfigApplicationContext` para arrancar el contexto sin XML.
- Clase `@Configuration` con métodos `@Bean` que definen los beans del contenedor.
- `@Value("${propiedad:default}")` para inyectar propiedades externas con valor por defecto.
- Inyección de dependencias por constructor: `User` recibe un `RolesInterface` al crearse.
- Recuperación de beans por tipo y por nombre (`getBean`).

### Polimorfismo / patrón Strategy
- `RolesInterface` define el contrato (`saludo()`, `printRoleName()`).
- `RolAdmin` y `RolNormal` son implementaciones intercambiables.
- `User` trabaja contra la interfaz, no contra implementaciones concretas.
- `RolType` centraliza los tipos de rol disponibles como enum (`ROL_NORMAL`, `ROL_ADMIN`).

### Estructura de datos
- `LRUCache<K, V>`: caché de capacidad fija con política *Least Recently Used*, implementada extendiendo `LinkedHashMap` con `accessOrder = true`. La capacidad se configura en `application.yml`.

### Aplicación de consola
- `Utils.scanner` es un `Scanner` estático compartido por todas las clases que leen de `stdin`.
- `Utils.processMenuOption` presenta un menú interactivo para leer e imprimir datos de usuario.

## Beans registrados

| Nombre bean | Tipo       | Descripción                            |
|-------------|------------|----------------------------------------|
| `normalRol` | `RolNormal`| Rol normal                             |
| `adminRol`  | `RolAdmin` | Rol administrador                      |
| `normalUser`| `User`     | Usuario por defecto con rol normal     |
| `adminUser` | `User`     | Usuario administrador con rol admin    |
| `lruCache`  | `LRUCache` | Caché LRU con capacidad desde `application.yml` |

## Configuración

`src/main/resources/application.yml`:

```yaml
lrucache:
  capacity: 10   # Capacidad máxima de la caché LRU (por defecto 100 si no se define)
```

## Cómo ejecutar

```bash
mvn compile exec:java -Dexec.mainClass="com.curso.App"
```

O desde el IDE ejecutando `App.main()`.