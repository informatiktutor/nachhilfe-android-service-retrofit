package de.informatiktutor.android_service_retrofit

import de.informatiktutor.android_service_retrofit.model.ProductResult
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {

    // Example request:
    // https://world.openfoodfacts.org/api/v0/product/4104420180918.json

    @GET("api/v0/product/{barcode}.json")
    fun product(@Path("barcode") barcode: String?): Call<ProductResult>

    companion object {
        fun create(): ProductService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://world.openfoodfacts.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(ProductService::class.java)
        }
    }
}
