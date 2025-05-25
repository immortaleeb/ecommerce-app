package com.ecommerce.catalog.internal

import com.ecommerce.catalog.api.ProductSummary

interface ProductRepository {
    fun findAll(): List<ProductSummary>
}
