package com.magicworld.clientsmanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.clientsmanager.model.Product
import com.magicworld.clientsmanager.repository.ProductListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListViewModel: ViewModel() {
    private val productListRepository = ProductListRepository()

    private val productsLoad = MutableLiveData<ArrayList<Product>>()
    val onProductsLoaded: LiveData<ArrayList<Product>> = productsLoad

    fun getProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            productsLoad.postValue(productListRepository.getProducts())
        }
    }
}