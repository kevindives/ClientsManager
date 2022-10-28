package com.magicworld.clientsmanager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.tasks.await

suspend fun example(){

    val db = Firebase.firestore
    data class User(
        val first:String,
        val middle: String,
        val last: String,
        val born: Int
    )
    val user = User("kevin" , "darley", "tejada" , 1992)

    //add un user con un id dado
    //db.collection("users").document(user.first).set(user)

    //borrar un user
    //db.collection("users").document(user.first).delete()

    //modificar un user se necesita un id
    /*val userRef = db.collection("users").document("9GvEHVuApL75rTkFq9UY")
    userRef.update("first" , "kevin")*/

    val listUserFirebase = db.collection("users").get().await()
    val listUser = arrayListOf<User>()

    for (element in listUserFirebase){
        listUser.add(element.toObject())
    }

    //para implementar las facturas
    /*var product by rememberSaveable { mutableStateOf("") }
    var amount by rememberSaveable { mutableStateOf("") }
    var valor by rememberSaveable { mutableStateOf("") }*/

    /*UpdateDivider()
        MyTextField(campo = product, placeholder = "Producto") { product = it }
        MyTextField(campo = amount, placeholder = "cantidad") { amount = it }
        MyTextField(campo = valor, placeholder = "Valor") { valor = it }*/

}

@Composable
fun Divider() {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
        Text(
            text = "FACTURAR",
            modifier = Modifier.padding(horizontal = 18.dp),
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFB5B5B5)
        )
        Divider(
            Modifier
                .background(Color(0xFFF9F9F9))
                .height(1.dp)
                .weight(1f)
        )
    }
}