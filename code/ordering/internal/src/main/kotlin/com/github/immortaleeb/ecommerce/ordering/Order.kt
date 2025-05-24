package com.github.immortaleeb.ecommerce.ordering

import com.github.immortaleeb.ecommerce.foundation.events.api.EventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.api.Loggers
import com.github.immortaleeb.ecommerce.vocabulary.OrderId
import com.github.immortaleeb.ecommerce.vocabulary.ProductId

class Order private constructor(
    loggers: Loggers,
    private val eventPublisher: EventPublisher,
    val id: OrderId,
    private val productId: ProductId,
    private val amount: StrictPositiveInt,
    private var status: OrderStatus
) {
    private val logger = loggers.get(Order::class)

    fun snapshot() = Snapshot(id = id, productId = productId, amount = amount, status = status)

    data class Snapshot(val id: OrderId, val productId: ProductId, val amount: StrictPositiveInt, val status: OrderStatus)

    class Factory(private val loggers: Loggers, private val eventPublisher: EventPublisher) {
        fun restore(snapshot: Snapshot) = Order(
            loggers = loggers,
            eventPublisher = eventPublisher,
            id = snapshot.id,
            productId = snapshot.productId,
            amount = snapshot.amount,
            status = snapshot.status
        )
        
        fun create(orderId: OrderId, productId: ProductId, amount: StrictPositiveInt) = Order(
            loggers = loggers,
            eventPublisher = eventPublisher,
            id = orderId,
            productId = productId,
            amount = amount,
            status = OrderStatus.Placed
        )
    }
}

enum class OrderStatus {
    Placed
}
