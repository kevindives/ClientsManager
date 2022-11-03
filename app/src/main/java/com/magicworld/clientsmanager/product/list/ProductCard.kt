package com.magicworld.clientsmanager.product.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.magicworld.clientsmanager.model.Product
import com.magicworld.clientsmanager.ui.theme.agua
import com.magicworld.clientsmanager.ui.utils.MyCardText

@Composable
fun MyProductCard(product: Product, onProductSelected: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onProductSelected() },
        border = BorderStroke(0.5.dp, Color.LightGray),
        shape = RoundedCornerShape(8),
        elevation = 4.dp
    ) {
        Column {
            HeaderProductCard()
            BodyProductCard(product)
            FooterProductCard(Modifier.align(Alignment.End))
        }

    }
}

@Composable
fun HeaderProductCard() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(agua))
}

@Composable
fun BodyProductCard(product: Product) {
    Column {
        Row {
            MyCardText(product.productName, Color.Black)
        }
        Row {
            MyCardText("Invetario:", Color.Black)
            MyCardText("${product.stock} ud", Color.Gray)
        }
        Row {
            MyCardText("Precio: ", Color.Black)
            MyCardText(" ${product.price} $", Color.Gray)
        }

    }
}

@Composable
fun FooterProductCard(modifier: Modifier) {
    Box(modifier = modifier
        .fillMaxWidth()
        .height(25.dp))
}
