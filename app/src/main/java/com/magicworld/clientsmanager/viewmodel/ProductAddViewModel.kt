package com.magicworld.clientsmanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.clientsmanager.model.Product
import com.magicworld.clientsmanager.repository.ProductAddRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductAddViewModel: ViewModel() {

    private val productAddRepository = ProductAddRepository ()

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> = _product

    fun saveProduct(product: Product) {
        _product.value = product
    }

    fun createProductInDatabase(newProduct: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productAddRepository.createProductInDatabase(newProduct)
        }
    }
}