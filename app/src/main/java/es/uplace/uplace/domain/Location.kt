package es.uplace.uplace.domain

data class Location (val id: Int, val latitude: Double, val longitude: Double, val postalCode: String,
                     val city: String, val fullAddress: String)
