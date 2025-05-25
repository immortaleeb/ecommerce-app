package com.ecommerce.catalog.internal

import com.ecommerce.catalog.api.ListProducts
import com.github.immortaleeb.ecommerce.foundation.queries.api.Query
import com.github.immortaleeb.ecommerce.foundation.queries.api.QueryExecutor

class CatalogQueryExecutor(private val productRepository: ProductRepository) : QueryExecutor {
    @Suppress("UNCHECKED_CAST")
    override fun <Result> execute(query: Query<Result>): Result {
        return when (query) {
            is ListProducts -> productRepository.findAll()
            else -> error("No query handler found for query $query")
        } as Result
    }
}