# Factory Method Example (Java / Maven)

## Overview
A small, well-structured example of the Factory Method design pattern implemented in Java with Maven. The project demonstrates how to decouple client code from concrete product implementations by using factory classes that create product instances.

This repository is intentionally minimal and focused on clarity: it shows the pattern, how to extend it, how to run and test it, and best-practice notes for production use.

## Table of Contents
- [Project Structure](#project-structure)
- [How It Works](#how-it-works)
- [Key Classes](#key-classes)
- [Build and Run (Windows)](#build-and-run-windows)
- [Examples](#examples)
- [Extending the Example](#extending-the-example)
- [Testing](#testing)
- [Notes and Best Practices](#notes-and-best-practices)
- [Contributing](#contributing)

## Project Structure
Important files and folders:

- `src/main/java/org/example/factorymethod/` - main Java sources
- `src/test/java/` - unit tests
- `pom.xml` - Maven build file
- `README.md` - this file

Inside `src/main/java/org/example/factorymethod/` you will typically find:
- `Transport` (interface)
- `TransportFactory` (abstract factory)
- Concrete factories: `CarFactory`, `PlaneFactory`
- Concrete products: `Car`, `Plane`
- `Client` — demonstrates usage

## How It Works
The Factory Method pattern defines an interface for creating an object, but lets subclasses decide which class to instantiate. This promotes loose coupling by removing direct dependencies on concrete implementations.

Typical flow:
1. Client depends on the abstract factory type `TransportFactory`.
2. Concrete factory (e.g., `CarFactory`) implements the factory method `createTransport()` and returns a concrete `Transport` (`Car`).
3. Client calls `createTransport()` and uses the returned `Transport` via the `Transport` interface only.

This makes adding new transport types straightforward: add a new `Transport` implementation and a corresponding `TransportFactory` implementation; client code does not need to change.

## Key Classes
- `Transport` — product interface with domain methods (e.g., `move()`).
- `TransportFactory` — abstract factory declaring `createTransport()`.
- `Car`, `Plane` — concrete products implementing `Transport`.
- `CarFactory`, `PlaneFactory` — concrete factories returning product instances.
- `Client` — example entry point that creates factories and uses products.

## Build and Run (Windows)
Prerequisites:
- Java 17+ (or configured project JDK)
- Maven 3.6+

Commands:

1. Build:
```bash
mvn clean package
```

2. Run with Maven (exec plugin):
```bash
mvn exec:java -Dexec.mainClass="org.example.factorymethod.Client"
```

3. Run the jar directly (replace `<artifactId>` and `<version>` from `pom.xml`):
```cmd
java -cp target\<artifactId>-<version>.jar org.example.factorymethod.Client
```

Expected console output (example):
```
Car is moving...
Plane is flying...
```

## Examples
Typical interface and factory shapes (illustrative):

```java
// src/main/java/org/example/factorymethod/Transport.java
public interface Transport {
    void move();
}
```

```java
// src/main/java/org/example/factorymethod/TransportFactory.java
public abstract class TransportFactory {
    public abstract Transport createTransport();
}
```

Client usage pattern:

```java
// usage (simplified)
TransportFactory factory = new CarFactory();
Transport t = factory.createTransport();
t.move();
```

## Extending the Example
To add a new transport type (e.g., `Boat`):

1. Create `Boat` implementing `Transport`.
2. Create `BoatFactory` extending `TransportFactory` and return new `Boat()` from `createTransport()`.
3. Use `BoatFactory` in the client or register it where factories are selected.

Optional: use a configuration or registry so the client selects factories by key rather than hard-coding them.

Example new classes (conceptual):

```java
public class Boat implements Transport {
    @Override
    public void move() {
        System.out.println("Boat is sailing...");
    }
}

public class BoatFactory extends TransportFactory {
    @Override
    public Transport createTransport() {
        return new Boat();
    }
}
```

## Testing
- Add unit tests under `src/test/java` using JUnit (or preferred test framework).
- Example tests:
    - Factory returns non-null product.
    - Product behavior (e.g., `move()` side effects or state changes) is as expected.
- Run:
```bash
mvn test
```

## Notes and Best Practices
- Keep `Transport` interface minimal and focused on behavior required by clients.
- Prefer immutability for product objects where applicable.
- For complex creation (configuration, dependencies), consider combining Factory Method with Dependency Injection or use the Builder pattern for product setup.
- If many factories are needed, introduce a `FactoryRegistry` or use an IoC container to avoid `if`/`switch` selection logic.
- Add logging instead of `System.out.println` for production code.
- Make factory methods thread-safe if factories are shared across threads.

## Contributing
- Follow standard Java conventions and include unit tests for new behavior.
- Open a pull request with a clear description of changes and rationale.

