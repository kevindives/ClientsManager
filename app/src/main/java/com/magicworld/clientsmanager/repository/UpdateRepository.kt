package com.magicworld.clientsmanager.repository

import com.google.firebase.FirebaseException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.magicworld.clientsmanager.model.User
import kotlinx.coroutines.tasks.await

class UpdateRepository {

    private val db = Firebase.firestore

    suspend fun updateUser(user: User):String {
        return try{
            db.collection("users").document(user.id).set(user).await()
            "El cliente ha sido modificado exitosamente"
        }catch (e: FirebaseException){
            e.message.toString()
        }
    }

    suspend fun updateSet(set: String, id: String) {
        try {
            db.collection("users").document(id).update("set" , set).await()
        }catch (e: FirebaseException){
            e.message.toString()
        }
    }

    suspend fun deleteUser(id: String) {
        try {
            db.collection("users").document(id).delete().await()
        }catch (e: FirebaseException){
            e.message.toString()
        }
    }

}