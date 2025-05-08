package com.github.immortaleeb.ecommerce.vocabulary

import earth.adi.typeid.RawId
import earth.adi.typeid.TypeId

@JvmInline
value class OrderId(val value: RawId) {
    init {
        check(value.prefix == PREFIX)
    }

    companion object {
        const val PREFIX = "order"
        fun generate() = OrderId(TypeId.generate(PREFIX))
        fun of(value: String) = OrderId(TypeId.parse(value))
    }
}

