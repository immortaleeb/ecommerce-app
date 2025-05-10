package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.shipping.OrderMother.shippedOrderToAtomium
import com.github.immortaleeb.ecommerce.shipping.OrderMother.unshippedOrderToAtomium
import com.github.immortaleeb.ecommerce.shipping.ShippingStatus.Shipped
import com.github.immortaleeb.ecommerce.vocabulary.OrderId
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Test

interface OrdersContractTest {
    val orders: Orders
    val orderFactory: Order.Factory

    @Test
    fun `getById throws OrderNotFound when order doesn't exist`() {
        val orderId = OrderId.generate()

        val thrown = catchThrowable {
            orders.getById(orderId)
        }

        assertThat(thrown).isInstanceOf(OrderNotFound::class.java)
        assertThat((thrown as OrderNotFound).orderId).isEqualTo(orderId)
    }

    @Test
    fun `create creates new order`() {
        orders.create(unshippedOrderToAtomium().id, unshippedOrderToAtomium().shippingAddress)

        assertThat(orders.getById(unshippedOrderToAtomium().id)).isNotNull
    }

    @Test
    fun `update updates existing order`() {
        val order = unshippedOrderToAtomium().restore()
        orders.create(unshippedOrderToAtomium().id, unshippedOrderToAtomium().shippingAddress)

        orders.update(shippedOrderToAtomium().restore())

        assertThat(orders.getById(order.id).snapshot().status).isEqualTo(Shipped)
    }

    fun Order.Snapshot.restore(): Order = orderFactory.restore(this)

}