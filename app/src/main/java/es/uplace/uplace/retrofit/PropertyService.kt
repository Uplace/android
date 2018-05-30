package es.uplace.uplace.retrofit

import es.uplace.uplace.Constants
import es.uplace.uplace.domain.Content
import es.uplace.uplace.domain.Property
import es.uplace.uplace.domain.Request
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface PropertyService {

    companion object Factory {
        fun create(): PropertyService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Constants.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(PropertyService::class.java)
        }
    }

    @GET("properties")
    fun findPropertiesByCriteria(@QueryMap params: HashMap<String, String>): Call<Content>

    @GET("properties/{reference}")
    fun findPropertyByReference(@Path("reference") reference: String): Call<Property>

    @POST("properties/{reference}/inquire")
    fun sendRequestInformation(@Path("reference") reference: String, @Body requestBody: String): Call<Void>
}
