package com.example.graphsdemo.data.model.product

import com.example.graphsdemo.data.model.credit.CreditCard
import com.google.gson.annotations.SerializedName

data class ProductResponse(
    @SerializedName("code")
    var code: Int = 0,
    @SerializedName("data")
    var data: List<Product> = listOf(),
    @SerializedName("status")
    var status: String = "",
    @SerializedName("total")
    var total: Int = 0
)