package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.foundation.commands.api.Command
import com.github.immortaleeb.ecommerce.foundation.commands.api.CommandExecutor
import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.api.Loggers

class ShippingCommandExecutor(
    private val loggers: Loggers,
    private val eventPublisher: EventPublisher
) : CommandExecutor {
    private val shipOrderHandler = ShipOrderHandler(loggers, eventPublisher)

    override fun execute(command: Command) {
        when (command) {
            is ShipOrder -> shipOrderHandler.handle(command)
            else -> throw IllegalArgumentException("Unknown command: $command")
        }
    }
}
