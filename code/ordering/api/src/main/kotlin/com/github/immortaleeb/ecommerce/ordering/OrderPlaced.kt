package com.github.immortaleeb.ecommerce.ordering

import com.github.immortaleeb.ecommerce.foundation.events.api.Event
import com.github.immortaleeb.ecommerce.vocabulary.ProductId

data class OrderPlaced(val productId: ProductId, val amount: StrictPositiveInt) : Event
