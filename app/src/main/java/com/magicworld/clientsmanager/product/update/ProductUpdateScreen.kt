package com.magicworld.clientsmanager.product.update

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import com.magicworld.clientsmanager.model.Product
import com.magicworld.clientsmanager.ui.utils.MyAlertDialog
import com.magicworld.clientsmanager.ui.utils.MyTextField
import com.magicworld.clientsmanager.viewmodel.ProductUpdateViewModel

@Composable
fun ProductUpdateScreen(
    navController: NavHostController,
    productUpdateViewModel: ProductUpdateViewModel,
    product: Product
) {
    val context = LocalContext.current
    val updateProduct by productUpdateViewModel.updateProduct.observeAsState(Product())

    Scaffold(
        topBar = {
            TopAppBarProductUpdate(product, navController, productUpdateViewModel){
                productUpdateViewModel.updateProductInDatabase(updateProduct)
                Toast.makeText(context, "Producto modificado correctamente", Toast.LENGTH_LONG).show()
                navController.navigateUp()
            }
        }
    ) { padding ->
        Box(modifier = Modifier.padding(padding)) {
           ProductUpdateBody ( productUpdateViewModel, product )
        }
    }
}


@Composable
fun TopAppBarProductUpdate(
    product: Product,
    navController: NavHostController,
    productUpdateViewModel: ProductUpdateViewModel,
    updateProduct:() -> Unit
) {

    var showDialog by rememberSaveable { mutableStateOf(false) }

    TopAppBar(
        title = { Text(text = "") },
        backgroundColor = Color.Transparent,
        elevation = 0.dp,
        navigationIcon = {
            IconButton(
                onClick = { updateProduct() }
            ) {
                Icon(painterResource(R.drawable.arrow_back_ios),
                    "ir atras",
                    tint = Color.Black,
                    modifier = Modifier.scale(0.5f, 0.5f))
            }
        },
        actions = {

            IconButton(onClick = { showDialog = true  }) {
                Icon(imageVector = Icons.Outlined.Delete,
                    contentDescription = "Borrar cliente",
                    tint = Color.DarkGray)
            }

        })

    MyAlertDialog(
        title = "Borrar ${product.productName}",
        description = "Estas seguro que quieres borrar ${product.productName}",
        show = showDialog,
        onDismiss = { showDialog = false }) {
        deleteProduct( navController, productUpdateViewModel, product)
    }
}

@Composable
fun ProductUpdateBody(productUpdateViewModel: ProductUpdateViewModel, product: Product) {

    var productName by rememberSaveable { mutableStateOf(product.productName) }
    var price by rememberSaveable { mutableStateOf(product.price) }
    var stock by rememberSaveable { mutableStateOf(product.stock) }

    val productUpdate = Product(product.id, productName, price, stock, product.quantity)

    productUpdateViewModel.saveProduct(productUpdate)

    Column(Modifier.fillMaxSize()) {
        MyTextField(campo = productName, placeholder = "Producto") { productName = it }
        MyTextField(campo = price, placeholder = "Precio", KeyboardType.Email) { price = it }
        MyTextField(campo = stock, placeholder = "Cantidad", KeyboardType.Number) { stock = it }
    }

}

fun deleteProduct(
    navController: NavHostController,
    productUpdateViewModel: ProductUpdateViewModel,
    product: Product
) {
    productUpdateViewModel.deleteProduct( product.id)
    navController.navigateUp()
}

