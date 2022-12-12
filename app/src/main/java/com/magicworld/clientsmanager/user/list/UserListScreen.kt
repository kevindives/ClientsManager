@file:Suppress("OPT_IN_IS_NOT_ENABLED")

package com.magicworld.clientsmanager.user.list


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.PersonAdd
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.magicworld.clientsmanager.model.Routes.*
import com.magicworld.clientsmanager.model.User
import com.magicworld.clientsmanager.ui.theme.aliceAzul
import com.magicworld.clientsmanager.ui.theme.turquesaMedio
import com.magicworld.clientsmanager.ui.utils.MyDrawer
import com.magicworld.clientsmanager.ui.utils.TopAppBarList
import com.magicworld.clientsmanager.viewmodel.UserListViewModel
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun UserListScreen(navController: NavHostController, userListViewModel: UserListViewModel) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBarList("Clients Manager",
                onClickDrawer = { coroutineScope.launch { scaffoldState.drawerState.open() } },
                onTitleClicked = {})
        },
        floatingActionButton = { UserListFAB(navController) },
        scaffoldState = scaffoldState,
        drawerContent = {
            MyDrawer(
                onUserListSelected = { coroutineScope.launch { scaffoldState.drawerState.close() } },
                onProductListSelected = { navController.navigate(ProductListScreen.route) })
        }
    ) { padding ->

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {

            UserBodyList(userListViewModel, navController)
        }
    }
}

@Composable
fun UserListFAB(navController: NavHostController) {
    FloatingActionButton(
        onClick = { navController.navigate(UserAddScreen.route) },
        backgroundColor = turquesaMedio
    ) {
        Icon(
            imageVector = Icons.Outlined.PersonAdd,
            contentDescription = "Crear cliente", tint = Color.White,
            modifier = Modifier.scale(1.5f, 1.5f)
        )
    }
}

@ExperimentalFoundationApi
@Composable
fun UserBodyList(userListViewModel: UserListViewModel, navController: NavHostController) {

    userListViewModel.getUserFromServer()
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
    MyUserCard(user,
        onEditSelected = {
            navController.currentBackStackEntry?.savedStateHandle?.set("user", user)
            navController.navigate(UserUpdateScreen.route)
        }, onUserSelected = {
            navController.currentBackStackEntry?.savedStateHandle?.set("user", user)
            navController.navigate(BillAddScreen.route)
        })
}

