package com.example.graphsdemo.data.model.credit

import com.example.graphsdemo.data.model.credit.CreditCard
import com.google.gson.annotations.SerializedName

data class CreditResponse(
    @SerializedName("code")
    var code: Int = 0,
    @SerializedName("data")
    var data: List<CreditCard> = listOf(),
    @SerializedName("status")
    var status: String = "",
    @SerializedName("total")
    var total: Int = 0
)