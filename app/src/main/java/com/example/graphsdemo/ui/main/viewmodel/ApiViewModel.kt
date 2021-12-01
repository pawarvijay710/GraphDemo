package com.example.graphsdemo.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.graphsdemo.data.model.credit.CreditResponse
import com.example.graphsdemo.data.model.product.ProductResponse
import com.example.graphsdemo.data.repository.ApiRepository
import com.mindorks.framework.mvvm.utils.NetworkHelper
import com.mindorks.framework.mvvm.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApiViewModel @Inject constructor(
    private val apiRepository: ApiRepository,
    private val networkHelper: NetworkHelper,
): ViewModel() {

    private var quantity: Int = 0
    private var taxes: Int = 0

    private val _apiData = MutableLiveData<Resource<ProductResponse>>()
    val apiData: LiveData<Resource<ProductResponse>> get() = _apiData

    private val _apiCreditCardData = MutableLiveData<Resource<CreditResponse>>()
    val apiCreditCardData: LiveData<Resource<CreditResponse>> get() = _apiCreditCardData

    fun setData(quantity: Int, taxes: Int){
        this.quantity = quantity
        this.taxes = taxes
    }

    fun fetchData() {
        viewModelScope.launch {
            _apiData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                apiRepository.getProducts(quantity, taxes).let {
                    if (it.isSuccessful) {
                        _apiData.postValue(Resource.success(it.body()))
                    } else
                        _apiData.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else
                _apiData.postValue(Resource.error("No internet connection", null))
        }
    }

    fun fetchCreditCard() {
        viewModelScope.launch {
            _apiCreditCardData.postValue(Resource.loading(null))
            if (networkHelper.isNetworkConnected()) {
                apiRepository.getCreditCard(quantity).let {
                    if (it.isSuccessful) {
                        _apiCreditCardData.postValue(Resource.success(it.body()))
                    } else
                        _apiCreditCardData.postValue(Resource.error(it.errorBody().toString(), null))
                }
            } else
                _apiCreditCardData.postValue(Resource.error("No internet connection", null))
        }
    }
}
