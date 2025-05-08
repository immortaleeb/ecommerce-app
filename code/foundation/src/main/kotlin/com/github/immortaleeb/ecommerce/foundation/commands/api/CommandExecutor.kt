package com.github.immortaleeb.ecommerce.foundation.commands.api

interface CommandExecutor {
    fun execute(command: Command)
}