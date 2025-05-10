# Architecture

This project is built as an event-driven modular monolith. It is not strictly event-driven, and we deviate where it makes sense.
Each module of the monolith is represented by a gradle subproject in the `code/` folder.
Functional modules such as `ordering` and `shipping` represent a bounded context and communication between bounded contexts happen mostly via events.

Right now the following modules exist:
- main: The main module that contains the entrypoint of the application. Its main responsibilities are to provide the main function and wire all the other modules together
- foundation: A module which contains "framework" type code which can be used by all other modules
- vocabulary: A module containing shared domain classes that are used across multiple functional modules
- ordering: A functional module which contains all logic related to ordering
- shipping: A functional module which contains all logic related to shipping orders
- infra: A folder containing modules that handles all I/O related concerns of the application (e.g., REST API, database implementations)

Functional modules are always split into two subprojects:
- api: contains the api of this module in the form of queries, commands, and events. Other modules can depend on this api for communication between modules.
- internal: The actual implementation of the api provided by the module. The only modules that are allowed to depend on the internal subproject are `main` (for wiring purposes) and `infra` modules (for implementing secondary adapters). No other module A should ever depend on the internal subproject of a module B.

## The foundation module

The foundation module contains some classes and interfaces which can be used for communication between modules:
- CommandExecutor: This interface can be used to execute commands from any module.
- EventPublisher: This interface is used to publish events

## The vocabulary module

The vocabulary module contains domain classes that are used across multiple functional modules:
- ProductId: A value class representing the unique identifier of a product
- OrderId: A value class representing the unique identifier of an order

This module helps avoid circular dependencies between functional modules by extracting common domain concepts into a separate module that can be depended on by multiple modules.

## Infra modules

Infra modules handles all I/O related concerns of the application, implementing the primary and secondary adapters of the hexagonal architecture. All infra modules exist as a subproject in the `code/infra` folder and are split by technology:

- inmemory: Contains in-memory implementations of repository interfaces defined in the functional modules
- (future subprojects could include: rest, jpa, mongodb, etc.)

Infra modules are one of only two types of modules (along with main) that is allowed to depend on the internal subprojects of functional modules. This is necessary to implement secondary adapters for interfaces defined in those internal modules.
