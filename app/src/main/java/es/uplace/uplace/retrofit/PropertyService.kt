package es.uplace.uplace.retrofit

import es.uplace.uplace.domain.Property
import retrofit2.Call
import retrofit2.http.GET

interface PropertyService {

    @GET("properties")
    fun findAllProperties(): Call<List<Property>>
}
