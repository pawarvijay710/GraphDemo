package com.example.graphsdemo.data.api

import com.example.graphsdemo.data.model.credit.CreditResponse
import com.example.graphsdemo.data.model.product.ProductResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImpl @Inject constructor(private val apiService: ApiService) : ApiHelper {

    override suspend fun getProducts(quantity: Int, taxes: Int): Response<ProductResponse> {
        return apiService.getProducts(quantity, taxes)
    }

    override suspend fun getCreditCard(quantity: Int): Response<CreditResponse> {
        return apiService.getCreditCard(quantity)
    }
}