package com.ecommerce.infra.inmemory

import com.ecommerce.catalog.api.ProductSummary
import com.ecommerce.catalog.internal.ProductRepository

class InMemoryProductRepository : ProductRepository {
    private val products = mutableListOf<ProductSummary>()

    override fun findAll(): List<ProductSummary> {
        return products.toList()
    }

    fun addProduct(product: ProductSummary) {
        products.add(product)
    }
}
