package com.magicworld.clientsmanager.repository

import com.google.firebase.FirebaseException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.magicworld.clientsmanager.model.Product
import kotlinx.coroutines.tasks.await

class ProductUpdateRepository {

    private val db = Firebase.firestore


    suspend fun updateProductInDatabase(updateProduct: Product) {
        try {
            db.collection("products").document(updateProduct.id).set(updateProduct).await()
        }catch (e:FirebaseException){
            e.message.toString()
        }
    }

    suspend fun deleteProduct(id: String) {
        try{
            db.collection("products").document(id).delete().await()
        }catch (e: FirebaseException){
            e.message.toString()
        }
    }

}