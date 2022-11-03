package com.magicworld.clientsmanager.user.add

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.magicworld.clientsmanager.R
import com.magicworld.clientsmanager.model.User
import com.magicworld.clientsmanager.ui.theme.primaveraVerde
import com.magicworld.clientsmanager.ui.utils.MyTextField
import com.magicworld.clientsmanager.viewmodel.UserAddViewModel

@Composable
fun UserAddScreen(navController: NavHostController, userAddViewModel: UserAddViewModel) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBarUserAdd( userAddViewModel){
                Toast.makeText(context, "Usuario guardado con exito" , Toast.LENGTH_LONG).show()
                navController.navigateUp()
            }
        }
    ) { padding ->

        Box(modifier = Modifier.padding(padding)) {
            UserAddBody(userAddViewModel)
        }
    }
}


@Composable
fun TopAppBarUserAdd(
    userAddViewModel: UserAddViewModel,
    showMessage: () -> Unit,
) {
    var isSet by rememberSaveable { mutableStateOf(false) }
    val newUser by userAddViewModel.user.observeAsState(initial = User())

    TopAppBar(
        title = { Text(text = "") },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(
                onClick = {
                    userAddViewModel.createUserInDatabase(newUser)
                    showMessage()
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
                    userAddViewModel.saveSet(isSet)
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
fun UserAddBody(userAddViewModel: UserAddViewModel) {

    var name by rememberSaveable { mutableStateOf("") }
    var email by rememberSaveable { mutableStateOf("") }
    var phone by rememberSaveable { mutableStateOf("") }
    var address by rememberSaveable { mutableStateOf("") }
    val set by userAddViewModel.isSet.observeAsState(initial = "otras")
    var user by rememberSaveable { mutableStateOf(User()) }

    user = User("", name, email, phone, address, set)
    userAddViewModel.saveUser(user)

    Column(Modifier.fillMaxSize()) {
        MyTextField(campo = name, placeholder = "Nombre") { name = it }
        MyTextField(campo = email, placeholder = "Correo", KeyboardType.Email) { email = it }
        MyTextField(campo = phone, placeholder = "Celular", KeyboardType.Number) { phone = it }
        MyTextField(campo = address, placeholder = "Direccion") { address = it }
    }
}



