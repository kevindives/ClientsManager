package com.magicworld.clientsmanager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.magicworld.clientsmanager.model.Product
import com.magicworld.clientsmanager.repository.ProductUpdateRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductUpdateViewModel: ViewModel() {

    private val productUpdateRepository = ProductUpdateRepository()

    private val _updateProduct = MutableLiveData<Product>()
    val updateProduct: LiveData<Product> = _updateProduct

    fun saveProduct(product: Product) {
        _updateProduct.value = product
    }

    fun deleteProduct(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            productUpdateRepository.deleteProduct(id)
        }
    }

    fun updateProductInDatabase(updateProduct: Product) {
        viewModelScope.launch(Dispatchers.IO) {
            productUpdateRepository.updateProductInDatabase(updateProduct)
        }
    }
}