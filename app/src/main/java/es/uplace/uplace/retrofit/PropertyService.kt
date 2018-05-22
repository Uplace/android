package es.uplace.uplace.retrofit

import es.uplace.uplace.domain.Content
import es.uplace.uplace.domain.Property
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface PropertyService {

    @GET("properties")
    fun findAllProperties(): Call<Content>

    companion object Factory {
        fun create(): PropertyService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Service.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(PropertyService::class.java)
        }
    }
}
