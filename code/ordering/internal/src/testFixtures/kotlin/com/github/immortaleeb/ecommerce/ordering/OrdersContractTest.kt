package com.github.immortaleeb.ecommerce.ordering

import com.github.immortaleeb.ecommerce.vocabulary.OrderId
import com.github.immortaleeb.ecommerce.vocabulary.ProductId
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

interface OrdersContractTest {
    val orders: Orders

    @Test
    fun `should create and retrieve an order`() {
        val orderId = OrderId.generate()
        val productId = ProductId.generate()
        val amount = 5.strictPositive
        
        orders.create(orderId, productId, amount)

        val retrievedOrder = orders.getById(orderId).snapshot()
        assertThat(retrievedOrder.id).isEqualTo(orderId)
        assertThat(retrievedOrder.productId).isEqualTo(productId)
        assertThat(retrievedOrder.amount).isEqualTo(amount)
    }
    
    @Test
    fun `should throw OrderNotFound when order does not exist`() {
        val orderId = OrderId.generate()
        
        assertThatThrownBy { orders.getById(orderId) }
            .isInstanceOf(OrderNotFound::class.java)
            .hasMessageContaining(orderId.toString())
    }
}
