package es.uplace.uplace.domain

data class Request(
        val property: Property,
        val email: String,
        val firstName: String,
        val lastName: String,
        val message: String,
        val phone: String,
        val origin: Int = 1
)