package com.magicworld.clientsmanager.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.magicworld.clientsmanager.model.Product
import kotlinx.coroutines.tasks.await

class ProductListRepository {
    private val db = Firebase.firestore

    suspend fun getProducts() :ArrayList<Product>{
        val result = db.collection("products").get().await()
        val productList = arrayListOf<Product>()
        for (product in result){
            productList.add(product.toObject())
        }
        productList.sortBy { it.productName }
        return productList
    }
}