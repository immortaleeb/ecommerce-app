package com.github.immortaleeb.ecommerce

import com.github.immortaleeb.ecommerce.foundation.commands.api.Command
import com.github.immortaleeb.ecommerce.foundation.commands.api.CommandExecutor
import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.api.Loggers
import com.github.immortaleeb.ecommerce.ordering.OrderingCommand
import com.github.immortaleeb.ecommerce.ordering.OrderingCommandExecutor
import com.github.immortaleeb.ecommerce.shipping.Orders as ShippingOrders
import com.github.immortaleeb.ecommerce.ordering.Orders as OrderingOrders
import com.github.immortaleeb.ecommerce.shipping.ShippingCommand
import com.github.immortaleeb.ecommerce.shipping.ShippingCommandExecutor

class DelegatingCommandExecutor(
    shippingOrders: ShippingOrders,
    orderingOrders: OrderingOrders,
    loggers: Loggers,
    eventPublisher: EventPublisher,
) : CommandExecutor {
    private val logger = loggers.get(DelegatingCommandExecutor::class)
    private val orderingCommandExecutor = OrderingCommandExecutor(loggers, eventPublisher, orderingOrders)
    private val shippingCommandExecutor = ShippingCommandExecutor(shippingOrders)

    override fun execute(command: Command) {
        logger.info("Executing command", "command" to command::class.simpleName!!)

        when (command) {
            is OrderingCommand -> orderingCommandExecutor.execute(command)
            is ShippingCommand -> shippingCommandExecutor.execute(command)
            else -> throw IllegalArgumentException("Unknown command type: ${command::class.simpleName}")
        }
    }
}
