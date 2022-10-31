@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.magicworld.clientsmanager.user.list


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.magicworld.clientsmanager.R
import com.magicworld.clientsmanager.model.Routes
import com.magicworld.clientsmanager.model.Routes.UserAddScreen
import com.magicworld.clientsmanager.model.User
import com.magicworld.clientsmanager.ui.theme.aliceAzul
import com.magicworld.clientsmanager.ui.theme.turquesaMedio
import com.magicworld.clientsmanager.viewmodel.UserListViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserListScreen(navController: NavHostController, userListViewModel: UserListViewModel) {

    Scaffold(
        topBar = { TopAppBarList() },
        floatingActionButton = { FloatingListBottom(navController) }
    ) { padding ->

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {

            BodyList(userListViewModel, navController)
        }
    }
}

@Composable
fun TopAppBarList() {

    TopAppBar(
        title = { ListTitle() },
        backgroundColor = aliceAzul,
        modifier = Modifier
            .padding(13.dp)
            .clip(RoundedCornerShape(100)),
        navigationIcon = {
            IconButton(onClick = {}
            ) {
                Icon(Icons.Outlined.Menu, null, tint = Color.DarkGray)
            }
        },
        actions = {

            IconButton(onClick = { }) {
                Image(
                    painter = painterResource(id = R.drawable.oscurologo),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(45.dp)
                )
            }
        }
    )
}

@Composable
fun ListTitle() {
    Text(text = "Clients Manager ", color = Color.DarkGray, fontSize = 15.sp)
}

@Composable
fun FloatingListBottom(navController: NavHostController) {
    FloatingActionButton(
        onClick = { navController.navigate(UserAddScreen.route) },
        backgroundColor = turquesaMedio
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "Crear cliente", tint = Color.White,
            modifier = Modifier.scale(2f, 2f)
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun BodyList(userListViewModel: UserListViewModel, navController: NavHostController) {

    userListViewModel.getUserFromFirebase()
    val list by userListViewModel.onUserLoaded.observeAsState(arrayListOf())
    val listUser: Map<String, List<User>> = list.groupBy { it.set }
    val isLoading by userListViewModel.isLoading.observeAsState(initial = true)

    if (isLoading) {
        Box(Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        LazyColumn(Modifier.fillMaxWidth()) {

            listUser.forEach { (set, myUser) ->
                stickyHeader {
                    Box(Modifier.background(aliceAzul)) {
                        Text(text = set, modifier = Modifier
                            .padding(16.dp)
                            .fillMaxWidth(),
                            color = Color.Black)
                    }

                }
                items(myUser) { user ->

                    ItemUser(user, navController)
                }
            }
        }
    }

}

@Composable
fun ItemUser(user: User, navController: NavHostController) {
    MyCard(user,
        onEditSelected = {
        navController.currentBackStackEntry?.savedStateHandle?.set("user", user)
        navController.navigate(Routes.UserUpdateScreen.route)
    }, onUserSelected = {})
}

