package de.informatiktutor.android_service_retrofit.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("_id")
    val barcode: String = "",
    @SerializedName("generic_name")
    val genericName: String = "",
    @SerializedName("generic_name_de")
    val genericNameGerman: String = ""
)
