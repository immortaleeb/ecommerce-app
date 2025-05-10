package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.foundation.logging.testing.LoggingTest
import com.github.immortaleeb.ecommerce.shipping.OrderMother.atomiumShippingAddress
import com.github.immortaleeb.ecommerce.shipping.OrderMother.unshippedOrderToAtomium
import com.github.immortaleeb.ecommerce.vocabulary.OrderId
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProvideShippingAddressTest : LoggingTest {
    private val context = TestContext()
    override val loggers = context.loggers

    @Test
    fun `creates an unshipped order`() {
        val orderId = unshippedOrderToAtomium().id
        provideShippingAddress(orderId, atomiumShippingAddress())

        val createdOrder = context.orders.getById(orderId).snapshot()
        assertThat(createdOrder).isEqualTo(unshippedOrderToAtomium())
    }

    private fun provideShippingAddress(orderId: OrderId, shippingAddress: ShippingAddress) {
        context.commandExecutor.execute(ProvideShippingAddress(orderId, shippingAddress))
    }
}

