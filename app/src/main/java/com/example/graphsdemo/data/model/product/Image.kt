package com.example.graphsdemo.data.model.product


import com.google.gson.annotations.SerializedName

data class Image(
    @SerializedName("description")
    var description: String = "",
    @SerializedName( "title")
    var title: String = "",
    @SerializedName( "url")
    var url: String = ""
)