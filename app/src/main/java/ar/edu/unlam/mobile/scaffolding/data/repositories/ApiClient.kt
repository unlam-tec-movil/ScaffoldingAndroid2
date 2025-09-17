package ar.edu.unlam.mobile.scaffolding.data.repositories

abstract class ApiClient {
    abstract fun get(endpoint: String): String

    abstract fun post(
        endpoint: String,
        body: String,
    )
}
