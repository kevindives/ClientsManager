package com.magicworld.clientsmanager.repository


import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.magicworld.clientsmanager.model.User
import kotlinx.coroutines.tasks.await

class UserListRepository {

    private val db = Firebase.firestore

    suspend fun getSuperheroesFromServer(): ArrayList<User> {

        val result = db.collection("users").get().await()
        val listUser: ArrayList<User> = arrayListOf()

        for (user in result) listUser.add(user.toObject())
        listUser.sortBy { it.set }

        return listUser
    }
}