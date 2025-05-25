package com.github.immortaleeb.ecommerce

import com.ecommerce.catalog.api.ProductSummary
import com.ecommerce.catalog.internal.CatalogQueryExecutor
import com.ecommerce.infra.inmemory.InMemoryProductRepository
import com.github.immortaleeb.ecommerce.vocabulary.ProductId

class CatalogContext {
    private val productRepository = InMemoryProductRepository()
    val queryExecutor = CatalogQueryExecutor(productRepository)

    init {
        // add some hardcoded sample data for now
        productRepository.addProduct(ProductSummary(ProductId.generate(), "The Lord of the Rings"))
        productRepository.addProduct(ProductSummary(ProductId.generate(), "Moby Dick"))
        productRepository.addProduct(ProductSummary(ProductId.generate(), "Frankenstein"))
    }
}
