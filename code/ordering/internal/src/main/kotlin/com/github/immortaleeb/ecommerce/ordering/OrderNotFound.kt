package com.github.immortaleeb.ecommerce.ordering

import com.github.immortaleeb.ecommerce.vocabulary.OrderId

class OrderNotFound(val orderId: OrderId) : RuntimeException("Could not find order with id $orderId")
