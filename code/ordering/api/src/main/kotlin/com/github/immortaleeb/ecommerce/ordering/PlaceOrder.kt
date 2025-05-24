package com.github.immortaleeb.ecommerce.ordering

import com.github.immortaleeb.ecommerce.vocabulary.OrderId
import com.github.immortaleeb.ecommerce.vocabulary.ProductId

@JvmInline
value class StrictPositiveInt(val value: Int) {
    init {
        check(value > 0) { "Positive Int must be positive" }
    }
}
inline val Int.strictPositive get() = StrictPositiveInt(this)

data class PlaceOrder(val orderId: OrderId, val productId: ProductId, val amount: StrictPositiveInt) : OrderingCommand
