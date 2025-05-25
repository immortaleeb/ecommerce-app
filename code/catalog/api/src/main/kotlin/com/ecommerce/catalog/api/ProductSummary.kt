package com.ecommerce.catalog.api

import com.github.immortaleeb.ecommerce.vocabulary.ProductId

data class ProductSummary(
    val id: ProductId,
    val description: String
)
