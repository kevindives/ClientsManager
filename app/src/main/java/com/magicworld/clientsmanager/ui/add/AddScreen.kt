package com.magicworld.clientsmanager.ui.add

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.magicworld.clientsmanager.R
import com.magicworld.clientsmanager.model.User
import com.magicworld.clientsmanager.ui.theme.primaveraVerde
import com.magicworld.clientsmanager.ui.utils.MyTextField
import com.magicworld.clientsmanager.viewmodel.AddViewModel

@Composable
fun AddClientScreen(navController: NavHostController, addViewModel: AddViewModel) {

    Scaffold(
        topBar = {
            TopAppBarAddView(navController, addViewModel)
        }
    ) { padding ->

        Box(modifier = Modifier.padding(padding)) {
            AddBody(addViewModel)
        }
    }
}


@Composable
fun TopAppBarAddView(navController: NavHostController, addViewModel: AddViewModel) {
    var isSet by rememberSaveable { mutableStateOf(false) }
    val user by addViewModel.user.observeAsState(initial = User())
    val newUser = user

    TopAppBar(
        title = { Text(text = "") },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(
                onClick = {
                    addViewModel.createUserInDatabase(newUser)
                    navController.navigateUp()
                }
            ) {
                Icon(painterResource(R.drawable.arrow_back_ios),
                    null,
                    tint = Color.Black,
                    modifier = Modifier.scale(0.6f, 0.6f))
            }
        },
        actions = {
            IconButton(
                onClick = {
                    isSet = !isSet
                    addViewModel.saveSet(isSet)
                }
            ) {
                Icon(painterResource(R.drawable.push_pin),
                    null,
                    tint = if (isSet) primaveraVerde else Color.DarkGray,
                    modifier = Modifier.scale(0.6f, 0.6f))
            }

        })
}

@Composable
fun AddBody(addViewModel: AddViewModel) {

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    val set by addViewModel.isSet.observeAsState(initial = "otras")
    var user by rememberSaveable { mutableStateOf(User()) }

    user = User("" , name, email, phone,address, set )
    addViewModel.saveUser(user)

    Column(Modifier.fillMaxSize()) {
        MyTextField(campo = name, placeholder = "Nombre") { name = it }
        MyTextField(campo = email, placeholder = "Correo", KeyboardType.Email) { email = it }
        MyTextField(campo = phone, placeholder = "Celular", KeyboardType.Number) { phone = it }
        MyTextField(campo = address, placeholder = "Direccion") { address = it }
    }
}



