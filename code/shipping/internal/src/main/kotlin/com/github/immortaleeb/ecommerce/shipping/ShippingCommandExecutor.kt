package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.foundation.commands.api.Command
import com.github.immortaleeb.ecommerce.foundation.commands.api.CommandExecutor

class ShippingCommandExecutor(
    orders: Orders,
) : CommandExecutor {
    private val shipOrderHandler = ShipOrderHandler(orders)

    override fun execute(command: Command) {
        when (command) {
            is ShipOrder -> shipOrderHandler.handle(command)
            else -> throw IllegalArgumentException("Unknown command: $command")
        }
    }
}
