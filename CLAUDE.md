# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Commands

```bash
# Compile
mvn compile

# Run all tests
mvn test

# Run a single test class
mvn test -Dtest=AppTest

# Run the application
mvn compile exec:java -Dexec.mainClass="com.curso.App"

# Package
mvn package
```

## Architecture

This is a **Spring Context 5.3.x** learning project (not Spring Boot) using **Java 21** and **Maven**. It demonstrates core Spring IoC concepts without the Boot auto-configuration layer.

**Entry point:** `App.java` — manually creates an `AnnotationConfigApplicationContext(BeansConfiguration.class)`, retrieves beans, and invokes the menu loop.

**Bean configuration:** All beans are declared in `BeansConfiguration.java` using `@Configuration`/`@Bean`. There is no component scanning — every bean is explicitly registered. The LRU cache capacity is injected via `@Value("${lrucache.capacity:100}")` from `application.yml`.

**Domain model:**
- `RolesInterface` / `RolType` (enum) — role contract and type enumeration
- `RolAdmin`, `RolNormal` — strategy implementations of `RolesInterface`
- `User` — composes a `RolesInterface` (has-a relationship, not inheritance)
- `UserService` — manages users in an `LRUCache<String, User>`; uses Java 21 pattern-matching switch for dispatch
- `LRUCache<K,V>` — extends `LinkedHashMap` with `removeEldestEntry()` override for eviction
- `Utils` — console I/O via `Scanner`; drives the interactive menu

**Test framework:** JUnit 4. Tests live under `src/test/java/com/curso/`.
