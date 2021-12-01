package com.example.graphsdemo.data.model.credit


import com.google.gson.annotations.SerializedName

data class CreditCard(
    @SerializedName("expiration")
    var expiration: String = "",
    @SerializedName("number")
    var number: String = "",
    @SerializedName("owner")
    var owner: String = "",
    @SerializedName("type")
    var type: String = ""
)