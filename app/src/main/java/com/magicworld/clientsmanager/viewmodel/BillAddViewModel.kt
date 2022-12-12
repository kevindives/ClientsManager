package com.magicworld.clientsmanager.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.magicworld.clientsmanager.model.Product

class BillAddViewModel: ViewModel() {

    private val _listBillProducts = mutableStateListOf<Product>()
    val listBillProducts: List<Product> = _listBillProducts

    fun saveBillProduct(product: Product) {
        _listBillProducts.add(product)
    }

    fun cleanList() {

        _listBillProducts.clear()
    }

    fun removeItemInList(product: Product) {
        _listBillProducts.remove(product)
    }


}