package com.magicworld.clientsmanager.repository


import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.magicworld.clientsmanager.model.User
import kotlinx.coroutines.tasks.await

class ListRepository {

    suspend fun getSuperheroesFromFireBase(): ArrayList<User> {
        val db = Firebase.firestore

        val result = db.collection("users").get().await()

        val listUser: ArrayList<User> = arrayListOf()
        for (user in result)
            listUser.add(user.toObject())

        return listUser
    }
}