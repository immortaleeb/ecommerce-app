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

    private val commandExecutor = OrderingCommandExecutor(loggers, eventPublisher) { FIXED_ORDER_ID }

    @Test
    fun `logs that the order was placed`() {
        commandExecutor.execute(PlaceOrder(productId = ProductId.generate(), amount = 2.strictPositive))

        assertThat(loggedLines).hasSize(1)
        assertThat(loggedLines[0].message).isEqualTo("Order placed")
    }

    @Test
    fun `publishes OrderPlaced event`() {
        val productId = ProductId.generate()
        commandExecutor.execute(PlaceOrder(productId = productId, amount = 2.strictPositive))

        assertThat(eventPublisher.publishedEvents).singleElement().isEqualTo(
            OrderPlaced(orderId = FIXED_ORDER_ID, productId = productId, amount = 2.strictPositive)
        )
    }
}
