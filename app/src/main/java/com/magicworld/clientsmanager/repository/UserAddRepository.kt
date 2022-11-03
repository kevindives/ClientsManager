package com.magicworld.clientsmanager.repository


import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.magicworld.clientsmanager.model.User

class UserAddRepository {

    private val db = Firebase.firestore

    fun createUserInDatabase(user: User) {
        try {

            val idUser = db.collection("users").document()
            val newUser =
                User(id = idUser.id, user.name, user.email, user.phone, user.address, user.set)

            db.collection("users").document(idUser.id).set(newUser)
        }catch (e:FirebaseAuthException){
            e.message.toString()
        }

    }
}