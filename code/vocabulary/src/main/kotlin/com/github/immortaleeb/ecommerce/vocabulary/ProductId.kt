package com.github.immortaleeb.ecommerce.vocabulary

import earth.adi.typeid.RawId
import earth.adi.typeid.TypeId

@JvmInline
value class ProductId(val value: RawId) {
    init {
        check(value.prefix == PREFIX)
    }

    companion object {
        const val PREFIX = "prdct"
        fun generate() = ProductId(TypeId.generate(PREFIX))
        fun of(value: String) = ProductId(TypeId.parse(value))
    }
}
