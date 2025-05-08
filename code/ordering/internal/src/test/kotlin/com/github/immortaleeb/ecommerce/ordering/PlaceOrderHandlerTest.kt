package com.github.immortaleeb.ecommerce.ordering

import com.github.immortaleeb.ecommerce.foundation.events.testing.FakeEventPublisher
import com.github.immortaleeb.ecommerce.foundation.logging.testing.LoggingTest
import com.github.immortaleeb.ecommerce.foundation.logging.testing.TestLoggers
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test


class PlaceOrderHandlerTest : LoggingTest {
    override val loggers: TestLoggers = TestLoggers()
    private val eventPublisher: FakeEventPublisher = FakeEventPublisher()

    private val commandExecutor = OrderingCommandExecutor(loggers, eventPublisher)

    @Test
    fun `logs that the order was placed`() {
        commandExecutor.execute(PlaceOrder(productId = ProductId("123"), amount = 2.strictPositive))

        assertThat(loggedLines).hasSize(1)
        assertThat(loggedLines[0].message).isEqualTo("Order placed")
    }

    @Test
    fun `publishes OrderPlaced event`() {
        commandExecutor.execute(PlaceOrder(productId = ProductId("123"), amount = 2.strictPositive))

        assertThat(eventPublisher.publishedEvents).singleElement().isEqualTo(
            OrderPlaced(productId = ProductId("123"), amount = 2.strictPositive)
        )
    }
}
