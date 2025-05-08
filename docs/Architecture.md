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

Functional modules are always split into two subprojects:
- api: contains the api of this module in the form of queries, commands, and events. Other modules can depend on this api for communication between modules.
- internal: The actual implementation of the api provided by the module. The only module that is allowed to depend on the internal subproject is `main` because it needs it for wiring purposes. No other module A should ever depend on the internal subproject of a module B.

## The foundation module

The foundation module contains some classes and interfaces which can be used for communication between modules:
- CommandExecutor: This interface can be used to execute commands from any module.
- EventPublisher: This interface is used to publish events
- OrderingCommand: Marker interface for commands that should be handled by the ordering module
- ShippingCommand: Marker interface for commands that should be handled by the shipping module

## The vocabulary module

The vocabulary module contains domain classes that are used across multiple functional modules:
- ProductId: A value class representing the unique identifier of a product
- OrderId: A value class representing the unique identifier of an order

This module helps avoid circular dependencies between functional modules by extracting common domain concepts into a separate module that can be depended on by multiple modules.

## Command Execution

Commands are executed through a unified command executor in the main module:
- DelegatingCommandExecutor: Routes commands to the appropriate module-specific executor based on the command type
  - OrderingCommands are routed to the OrderingCommandExecutor
  - ShippingCommands are routed to the ShippingCommandExecutor
