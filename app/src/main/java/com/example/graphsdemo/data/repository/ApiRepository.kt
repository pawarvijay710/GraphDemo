package com.example.graphsdemo.data.repository

import com.example.graphsdemo.data.api.ApiHelper
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiHelper: ApiHelper) {
    suspend fun getProducts(quantity: Int, taxes: Int) = apiHelper.getProducts(quantity, taxes)
    suspend fun getCreditCard(quantity: Int) = apiHelper.getCreditCard(quantity)
}