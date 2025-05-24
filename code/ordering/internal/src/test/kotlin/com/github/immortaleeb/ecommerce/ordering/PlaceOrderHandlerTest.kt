package com.github.immortaleeb.ecommerce.ordering

import com.github.immortaleeb.ecommerce.foundation.events.testing.FakeEventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.testing.LoggingTest
import com.github.immortaleeb.ecommerce.foundation.logging.testing.TestLoggers
import com.github.immortaleeb.ecommerce.vocabulary.OrderId
import com.github.immortaleeb.ecommerce.vocabulary.ProductId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

private val FIXED_ORDER_ID = OrderId.of("order_01jtrtsrywfzqa7fcqfpzn0m6q")

class PlaceOrderHandlerTest : LoggingTest {
    override val loggers: TestLoggers = TestLoggers()
    private val eventPublisher: FakeEventPublisher = FakeEventPublisher()
    private val orders = TestOrders(Order.Factory(loggers, eventPublisher))

    private val commandExecutor = OrderingCommandExecutor(loggers, eventPublisher, orders)

    @Test
    fun `logs that the order was placed`() {
        placeOrder(orderId = FIXED_ORDER_ID, productId = ProductId.generate(), amount = 2.strictPositive)

        assertThat(loggedLines).hasSize(1)
        assertThat(loggedLines[0].message).isEqualTo("Order placed")
    }

    @Test
    fun `publishes OrderPlaced event`() {
        val productId = ProductId.generate()
        placeOrder(orderId = FIXED_ORDER_ID, productId = productId, amount = 2.strictPositive)

        assertThat(eventPublisher.publishedEvents).singleElement().isEqualTo(
            OrderPlaced(orderId = FIXED_ORDER_ID, productId = productId, amount = 2.strictPositive)
        )
    }
    
    @Test
    fun `creates an order`() {
        val productId = ProductId.generate()
        placeOrder(orderId = FIXED_ORDER_ID, productId = productId, amount = 2.strictPositive)
        
        val order = orders.getById(FIXED_ORDER_ID).snapshot()
        assertThat(order.id).isEqualTo(FIXED_ORDER_ID)
        assertThat(order.productId).isEqualTo(productId)
        assertThat(order.amount).isEqualTo(2.strictPositive)
    }

    private fun placeOrder(orderId: OrderId, productId: ProductId, amount: StrictPositiveInt) {
        commandExecutor.execute(PlaceOrder(orderId = orderId, productId = productId, amount = amount))
    }
}
