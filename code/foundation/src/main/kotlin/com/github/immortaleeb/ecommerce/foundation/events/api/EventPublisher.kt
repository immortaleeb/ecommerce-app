package com.github.immortaleeb.ecommerce.foundation.events.api

interface EventPublisher {
    fun publish(event: Event)
}