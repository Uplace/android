package es.uplace.uplace.retrofit

import es.uplace.uplace.domain.Content
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface PropertyService {

    companion object Factory {
        fun create(): PropertyService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Service.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(PropertyService::class.java)
        }
    }

//    @GET("properties")
//    fun findAllProperties(): Call<Content>

    @GET("properties")
    fun findAllProperties(@QueryMap params: Map<String, String>): Call<Content>
}
