package es.uplace.uplace.retrofit

import es.uplace.uplace.domain.Filters
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface FiltersService {

    companion object Factory {
        fun create(): FiltersService {
            val retrofit = Retrofit.Builder()
                    .baseUrl(Service.API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()

            return retrofit.create(FiltersService::class.java)
        }
    }

    @GET("filters")
    fun findFilters(): Call<Filters>
}