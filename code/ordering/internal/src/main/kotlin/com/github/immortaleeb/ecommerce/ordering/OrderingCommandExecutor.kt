package com.github.immortaleeb.ecommerce.ordering

import com.github.immortaleeb.ecommerce.foundation.commands.api.Command
import com.github.immortaleeb.ecommerce.foundation.commands.api.CommandExecutor
import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.api.Loggers
import com.github.immortaleeb.ecommerce.vocabulary.OrderId

class OrderingCommandExecutor(
    loggers: Loggers,
    eventPublisher: EventPublisher,
    generateOrderId: () -> OrderId = { OrderId.generate() }
) : CommandExecutor {
    private val placeOrder: PlaceOrderHandler = PlaceOrderHandler(loggers, eventPublisher, generateOrderId)

    override fun execute(command: Command) {
        when (command) {
            is PlaceOrder -> placeOrder.handle(command)
            else -> error("No command handler found for command $command")
        }
    }
}
