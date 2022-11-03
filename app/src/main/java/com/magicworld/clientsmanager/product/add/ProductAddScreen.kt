package com.magicworld.clientsmanager.product.add

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
import com.magicworld.clientsmanager.model.Product
import com.magicworld.clientsmanager.ui.utils.MyTextField
import com.magicworld.clientsmanager.viewmodel.ProductAddViewModel


@Composable
fun ProductAppScreen( navController: NavHostController, productAddViewModel: ProductAddViewModel) {
    val context = LocalContext.current
    val newProduct by productAddViewModel.product.observeAsState( Product())

    Scaffold(
        topBar = {
            TopAppBarProductAdd{
                productAddViewModel.createProductInDatabase( newProduct )
                Toast.makeText(context, "Producto guardado con exito", Toast.LENGTH_LONG).show()
                navController.navigateUp()
            }
        }
    ) { padding ->

        Box(modifier = Modifier.padding(padding)) {
            ProductAddBody(productAddViewModel)
        }
    }
}


@Composable
fun TopAppBarProductAdd(createProductInDatabase: () -> Unit) {
    TopAppBar(
        title = { Text(text = "") },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(
                onClick = { createProductInDatabase() }
            ) {
                Icon(painterResource(R.drawable.arrow_back_ios),
                    null,
                    tint = Color.Black,
                    modifier = Modifier.scale(0.6f, 0.6f))
            }
        })
}

@Composable
fun ProductAddBody(productAddViewModel: ProductAddViewModel) {
    var productName by rememberSaveable { mutableStateOf("") }
    var price by rememberSaveable { mutableStateOf("") }
    var stock by rememberSaveable { mutableStateOf("") }

    val product = Product("", productName, price, stock, "")

    productAddViewModel.saveProduct(product)

    Column(Modifier.fillMaxSize()) {
        MyTextField(campo = productName, placeholder = "Producto") { productName = it }
        MyTextField(campo = price, placeholder = "Precio", KeyboardType.Email) { price = it }
        MyTextField(campo = stock, placeholder = "Cantidad", KeyboardType.Number) { stock = it }
    }

}


