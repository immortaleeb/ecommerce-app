package com.github.immortaleeb.ecommerce.foundation.queries.api

interface QueryExecutor {
    fun <Result> execute(query: Query<Result>): Result
}
