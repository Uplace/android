package es.uplace.uplace.domain

import com.google.gson.Gson

data class Request(
        val email: String,
        val firstName: String,
        val lastName: String,
        val message: String,
        val phone: String,
        val origin: Int = 1
) {
    override fun toString(): String {
        return Gson().toJson(this)
    }
}