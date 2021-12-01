package com.example.graphsdemo.data.api

import com.example.graphsdemo.data.model.credit.CreditResponse
import com.example.graphsdemo.data.model.product.ProductResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("api/v1/products")
    suspend fun getProducts(
        @Query("_quantity") quantity: Int,
        @Query("_taxes") taxes: Int,
        @Query("_locale") locale: String = "en_US",
        @Query("_categories_type") categories_type: String = "string"
    ): Response<ProductResponse>

    @GET("api/v1/credit_cards")
    suspend fun getCreditCard(
        @Query("_quantity") quantity: Int,
        @Query("_locale") locale: String = "en_US",
    ): Response<CreditResponse>
}