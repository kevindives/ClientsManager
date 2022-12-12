package com.magicworld.clientsmanager.ui.utils


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.magicworld.clientsmanager.model.Product
import com.magicworld.clientsmanager.ui.theme.fantasmaBlanco

@Composable
fun MyDropDownMenu(
    showMenu: Boolean,
    productList: ArrayList<Product>,
    onDismissMenu: () -> Unit,
    onProductSelected: (Product) -> Unit,
) {

    var show by rememberSaveable { mutableStateOf(false) }
    var currentProduct by rememberSaveable { mutableStateOf(Product()) }

    Column(Modifier.fillMaxWidth()) {
        DropdownMenu(
            expanded = showMenu,
            onDismissRequest = { onDismissMenu() },
            modifier = Modifier
                .fillMaxWidth()
                .background(fantasmaBlanco)
        ) {

            DropdownMenuItem(onClick = {}) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    productList.forEach { product ->
                        Text(
                            text = product.productName,
                            modifier = Modifier
                                .padding(vertical = 8.dp, horizontal = 16.dp)
                                .clickable {
                                    show = true
                                    currentProduct = product
                                }
                        )
                    }
                }
            }
        }
    }
    MyQuantityDialog(show = show, currentProduct,
        onDismiss = { show = false },
        onConfirm = { product ->
            onProductSelected(product)
            onDismissMenu()
            show = false
        }
    )
}



