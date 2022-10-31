package com.magicworld.clientsmanager.repository

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.magicworld.clientsmanager.model.User

class UserAddRepository {

    fun createUserInDatabase(user: User) {
        val db = Firebase.firestore

        val idUser = db.collection("user").document()

        val newUser =
            User(id = idUser.id, user.name, user.email, user.phone, user.address, user.set)

        db.collection("users").document(idUser.id).set(newUser)
    }
}