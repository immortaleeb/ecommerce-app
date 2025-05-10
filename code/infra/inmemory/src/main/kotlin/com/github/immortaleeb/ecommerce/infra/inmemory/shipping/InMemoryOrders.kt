package com.github.immortaleeb.ecommerce.infra.inmemory.shipping

import com.github.immortaleeb.ecommerce.shipping.Order
import com.github.immortaleeb.ecommerce.shipping.OrderNotFound
import com.github.immortaleeb.ecommerce.shipping.Orders
import com.github.immortaleeb.ecommerce.shipping.ShippingAddress
import com.github.immortaleeb.ecommerce.vocabulary.OrderId
import java.util.concurrent.ConcurrentHashMap

class InMemoryOrders(private val orderFactory: Order.Factory) : Orders {
    private val orders = ConcurrentHashMap<OrderId, Order.Snapshot>()

    override fun getById(orderId: OrderId): Order = orderFactory.restore(orders[orderId] ?: throw OrderNotFound(orderId))
    override fun create(
        orderId: OrderId,
        shippingAddress: ShippingAddress,
    ) = orderFactory.create(orderId, shippingAddress).also { orders[it.id] = it.snapshot() }

    override fun update(order: Order) {
        orders[order.id] = order.snapshot()
    }

    fun assumeExisting(order: Order) {
        orders[order.id] = order.snapshot()
    }
}
