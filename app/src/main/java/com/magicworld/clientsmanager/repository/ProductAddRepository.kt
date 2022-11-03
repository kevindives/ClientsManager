package com.magicworld.clientsmanager.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.magicworld.clientsmanager.model.Product

class ProductAddRepository {

    private val db = Firebase.firestore

    fun createProductInDatabase(product: Product) {
        val idProduct = db.collection("products").document()
        val newProduct = Product (idProduct.id , product.productName, product.price, product.stock ,"")
        db.collection("products").document(idProduct.id).set(newProduct)
    }
}