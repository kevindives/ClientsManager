package com.magicworld.clientsmanager.product.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.magicworld.clientsmanager.model.Product
import com.magicworld.clientsmanager.model.Routes.*
import com.magicworld.clientsmanager.ui.theme.turquesaMedio
import com.magicworld.clientsmanager.ui.utils.MyDrawer
import com.magicworld.clientsmanager.ui.utils.TopAppBarList
import com.magicworld.clientsmanager.viewmodel.ProductListViewModel
import kotlinx.coroutines.launch


@Composable
fun ProductListScreen(
    navController: NavHostController,
    productListViewModel: ProductListViewModel,
) {

    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBarList("Productos",
                onClickDrawer = { coroutineScope.launch { scaffoldState.drawerState.open() } },
                onTitleClicked = {})
        },
        floatingActionButton = { ProductListFAB(navController) },
        scaffoldState = scaffoldState,
        drawerContent = {
            MyDrawer(
                onUserListSelected = { navController.navigate(UserListScreen.route) },
                onProductListSelected = { coroutineScope.launch { scaffoldState.drawerState.close() } })
        }
    ) { padding ->

        Box(modifier = Modifier
            .fillMaxSize()
            .padding(padding)) {
            ProductBodyList(navController, productListViewModel)
        }
    }
}

@Composable
fun ProductListFAB(navController: NavHostController) {
    FloatingActionButton(
        onClick = { navController.navigate(ProductAddScreen.route) },
        backgroundColor = turquesaMedio
    ) {
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = "Crear producto", tint = Color.White,
            modifier = Modifier.scale(2f, 2f)
        )
    }
}

@Composable
fun ProductBodyList(navController: NavHostController, productListViewModel: ProductListViewModel) {

    productListViewModel.getProducts()
    val listProducts by productListViewModel.onProductsLoaded.observeAsState(arrayListOf())

    LazyColumn(Modifier.fillMaxWidth()) {
        items(listProducts) { product ->
            ItemProduct(product, navController)
        }
    }
}

@Composable
fun ItemProduct(product: Product, navController: NavHostController) {
    MyProductCard(product){
        navController.currentBackStackEntry?.savedStateHandle?.set("product", product)
        navController.navigate(ProductUpdateScreen.route)
    }
}



