package com.example.graphsdemo.data.model.product



import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName( "categories")
    var categories: List<String> = listOf(),
    @SerializedName("description")
    var description: String = "",
    @SerializedName("ean")
    var ean: String = "",
    @SerializedName("image")
    var image: String = "",
    @SerializedName("images")
    var images: List<Image> = listOf(),
    @SerializedName("name")
    var name: String = "",
    @SerializedName("net_price")
    var netPrice: Double = 0.0,
    @SerializedName( "price")
    var price: String = "",
    @SerializedName( "tags")
    var tags: List<String> = listOf(),
    @SerializedName( "taxes")
    var taxes: String = "",
    @SerializedName( "upc")
    var upc: String = ""
)