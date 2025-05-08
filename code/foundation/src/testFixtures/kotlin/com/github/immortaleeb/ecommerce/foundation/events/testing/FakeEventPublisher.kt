package com.github.immortaleeb.ecommerce.foundation.events.testing

import com.github.immortaleeb.ecommerce.foundation.events.api.Event
import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher

class FakeEventPublisher : EventPublisher {
    private val _publishedEvents = mutableListOf<Event>()
    val publishedEvents: List<Event> get() = _publishedEvents

    override fun publish(event: Event) {
        _publishedEvents.add(event)
    }
}
