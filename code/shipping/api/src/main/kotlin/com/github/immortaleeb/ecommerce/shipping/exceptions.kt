package com.github.immortaleeb.ecommerce.shipping

import com.github.immortaleeb.ecommerce.vocabulary.OrderId

class OrderNotFound(val orderId: OrderId) : RuntimeException("Could not find order with id $orderId")

class OrderAlreadyShipped(val orderId: OrderId) : RuntimeException("Order with id $orderId has already been shipped")
