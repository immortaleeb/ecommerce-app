package com.github.immortaleeb.ecommerce

import com.github.immortaleeb.ecommerce.foundation.commands.api.Command
import com.github.immortaleeb.ecommerce.foundation.commands.api.CommandExecutor
import com.github.immortaleeb.ecommerce.foundation.logging.api.Loggers
import com.github.immortaleeb.ecommerce.ordering.OrderingCommand
import com.github.immortaleeb.ecommerce.shipping.ShippingCommand

class DelegatingCommandExecutor(
    loggers: Loggers,
    private val ordering: OrderingContext,
    private val shipping: ShippingContext,
) : CommandExecutor {
    private val logger = loggers.get(DelegatingCommandExecutor::class)

    override fun execute(command: Command) {
        logger.info("Executing command", "command" to command::class.simpleName!!)

        when (command) {
            is OrderingCommand -> ordering.commandExecutor.execute(command)
            is ShippingCommand -> shipping.commandExecutor.execute(command)
            else -> throw IllegalArgumentException("Unknown command type: ${command::class.simpleName}")
        }
    }
}
