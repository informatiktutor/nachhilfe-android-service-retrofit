package de.informatiktutor.android_service_retrofit.model

import com.google.gson.annotations.SerializedName

data class ProductResult(
    @SerializedName("code")
    val code: String = "",
    @SerializedName("product")
    val product: Product = Product(),
    @SerializedName("status")
    val status: Int = 0,
    @SerializedName("status_verbose")
    val statusText: String = ""
)
