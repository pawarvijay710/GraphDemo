package com.example.graphsdemo.data.api

import com.example.graphsdemo.data.model.credit.CreditResponse
import com.example.graphsdemo.data.model.product.ProductResponse
import retrofit2.Response

interface ApiHelper {
    suspend fun getProducts(quantity: Int, taxes: Int): Response<ProductResponse>

    suspend fun getCreditCard(quantity: Int): Response<CreditResponse>
}