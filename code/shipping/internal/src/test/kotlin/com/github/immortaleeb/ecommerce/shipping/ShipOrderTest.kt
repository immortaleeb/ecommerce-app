package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.foundation.events.testing.FakeEventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.testing.LoggingTest
import com.github.immortaleeb.ecommerce.foundation.logging.testing.TestLoggers
import com.github.immortaleeb.ecommerce.foundation.logging.testing.TestLoggers.LoggedLine
import com.github.immortaleeb.ecommerce.shipping.OrderMother.unshippedOrderToAtomium
import com.github.immortaleeb.ecommerce.shipping.ShippingStatus.Shipped
import com.github.immortaleeb.ecommerce.shipping.Order
import com.github.immortaleeb.ecommerce.vocabulary.OrderId
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.junit.jupiter.api.Test

class ShipOrderTest : LoggingTest {
    override val loggers: TestLoggers = TestLoggers()
    private val eventPublisher: FakeEventPublisher = FakeEventPublisher()
    private val orders = TestOrders(Order.Factory(loggers, eventPublisher))

    private val commandExecutor = ShippingCommandExecutor(orders)

    @Test
    fun `fails when order is not found`() {
        val orderId = OrderId.generate()

        val thrown = catchThrowable {
            shipOrder(orderId)
        }

        assertThat(thrown).isInstanceOf(OrderNotFound::class.java)
        assertThat((thrown as OrderNotFound).orderId).isEqualTo(orderId)
    }

    @Test
    fun `cannot ship an order that has already been shipped`() {
        val order = unshippedOrderToAtomium()
        orders.assumeExisting(order)
        shipOrder(order.id)

        val thrown = catchThrowable { shipOrder(order.id) }

        assertThat(thrown).isInstanceOf(OrderAlreadyShipped::class.java)
        assertThat((thrown as OrderAlreadyShipped).orderId).isEqualTo(order.id)
    }

    @Test
    fun `updates the status to shipped when order is shipped`() {
        orders.assumeExisting(unshippedOrderToAtomium())

        shipOrder(unshippedOrderToAtomium().id)

        assertThat(orderStatusOf(unshippedOrderToAtomium().id)).isEqualTo(Shipped)
    }

    @Test
    fun `publishes event when order is shipped`() {
        orders.assumeExisting(unshippedOrderToAtomium())

        shipOrder(unshippedOrderToAtomium().id)

        assertThat(eventPublisher.publishedEvents).singleElement().isEqualTo(OrderShipped(unshippedOrderToAtomium().id))
    }

    @Test
    fun `logs when order is shipped`() {
        orders.assumeExisting(unshippedOrderToAtomium())

        shipOrder(unshippedOrderToAtomium().id)

        assertThat(loggedLines).singleElement().isEqualTo(
            LoggedLine(
                message = "Order shipped",
                context = mapOf(
                    "orderId" to unshippedOrderToAtomium().id,
                    "address" to unshippedOrderToAtomium().shippingAddress
                )
            )
        )
    }

    private fun orderStatusOf(orderId: OrderId): ShippingStatus = orders.getById(orderId).snapshot().status

    private fun shipOrder(orderId: OrderId) = commandExecutor.execute(ShipOrder(orderId))
}
