package com.magicworld.clientsmanager.user.update

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
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
import com.magicworld.clientsmanager.ui.utils.MyAlertDialog
import com.magicworld.clientsmanager.ui.utils.MyTextField
import com.magicworld.clientsmanager.viewmodel.UserUpdateViewModel

@Composable
fun UserUpdateScreen(navController: NavHostController, userUpdateViewModel: UserUpdateViewModel, user: User) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBarUpdateView(navController, user ,userUpdateViewModel)
        },
        floatingActionButton = {
            UpdateFloatingButton(userUpdateViewModel) {
                Toast.makeText(context, "Los cambios han sido guardados", Toast.LENGTH_LONG).show()
                navController.navigateUp()
            }
        },
        floatingActionButtonPosition = FabPosition.End
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
            UpdateBody(user, userUpdateViewModel)
        }
    }
}

@Composable
fun TopAppBarUpdateView(
    navController: NavHostController,
    user: User,
    userUpdateViewModel: UserUpdateViewModel
) {

    var isSet by rememberSaveable { mutableStateOf(false) }
    isSet = user.set == "fijadas"
    var showDialog by rememberSaveable { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = "") },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(onClick = { navController.navigateUp() }
            ) {
                Icon(painterResource(R.drawable.arrow_back_ios),
                    "ir atras",
                    tint = Color.Black,
                    modifier = Modifier.scale(0.5f, 0.5f))
            }
        },
        actions = {
            IconButton(
                onClick = {
                    isSet = !isSet
                    userUpdateViewModel.updateSet(isSet, user.id)
                }) {
                Icon(painterResource(R.drawable.push_pin),
                    "fijar en lista",
                    tint = if (isSet) primaveraVerde else Color.DarkGray,
                    modifier = Modifier.scale(0.5f, 0.5f))
            }
            IconButton(onClick = { showDialog = true  }) {
                Icon(imageVector = Icons.Outlined.Delete,
                    contentDescription = "Borrar cliente",
                    tint = Color.DarkGray)
            }

        })

    MyAlertDialog(
        title = "Borrar ${user.name}",
        description = "Estas seguro que quieres borrar ${user.name}",
        show = showDialog,
        onDismiss = { showDialog = false }) {
        deleteUser(user, userUpdateViewModel, navController)
    }

}

@Composable
fun UpdateFloatingButton(userUpdateViewModel: UserUpdateViewModel, showMessage: () -> Unit) {

    val updateUser by userUpdateViewModel.updateUser.observeAsState(initial = User())
    val user: User = updateUser

    FloatingActionButton(
        onClick = {
            userUpdateViewModel.updateUser(user)
            showMessage()
        },
        backgroundColor = MaterialTheme.colors.primary) {

        Icon(painterResource(R.drawable.save),
            contentDescription = "Guardar cliente",
            tint = Color.White,
            modifier = Modifier.scale(0.7f, 0.7f)
        )

    }

}

@Composable
fun UpdateBody(user: User, userUpdateViewModel: UserUpdateViewModel) {
    var name by rememberSaveable { mutableStateOf(user.name) }
    var email by rememberSaveable { mutableStateOf(user.email) }
    var phone by rememberSaveable { mutableStateOf(user.phone) }
    var address by rememberSaveable { mutableStateOf(user.address) }
    var updateUser by rememberSaveable { mutableStateOf(User()) }
    updateUser = User(user.id, name, email, phone, address, user.set)

    userUpdateViewModel.saveUpdateUser(updateUser)

    Column(Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        MyTextField(campo = name, placeholder = "Nombre") { name = it }
        MyTextField(campo = email, placeholder = "Correo", KeyboardType.Email) { email = it }
        MyTextField(campo = phone, placeholder = "Celular", KeyboardType.Number) { phone = it }
        MyTextField(campo = address, placeholder = "Producto") { address = it }

    }

}

fun deleteUser(user: User, userUpdateViewModel: UserUpdateViewModel, navController: NavHostController) {
    userUpdateViewModel.deleteUser(user.id)
    navController.popBackStack()
}


