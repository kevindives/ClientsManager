package com.magicworld.clientsmanager.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.magicworld.clientsmanager.model.Product

@Composable
fun MyAlertDialog(title:String, description:String ,show: Boolean, onDismiss: () -> Unit, onConfirm: () -> Unit){

    if (show) {

        AlertDialog(
            onDismissRequest = { onDismiss() },
            title = { Text(text = title) },
            text = { Text(text = description) },
            confirmButton = {
                TextButton(onClick = { onConfirm() }) {
                    Text(text = "Confirmar")
                }
            },
            dismissButton = {
                TextButton(onClick = { onDismiss() }) {
                    Text(text = "Rechazar")
                }
            }
        )
    }

}

@Composable
fun MyQuantityDialog(
    show: Boolean,
    currentProduct: Product,
    onDismiss: () -> Unit,
    onConfirm: (Product) -> Unit,
) {

    var quantity by remember { mutableStateOf("") }

    if (show) {

        Dialog(onDismissRequest = { onDismiss() }) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .background(color = Color.White)
            ) {
                MyTitleDialog(title = "Selecione cantidad de ${currentProduct.productName}" )

                MyTextField( quantity ,"Cantidad", KeyboardType.Number) { quantity = it }
                Divider(Modifier.fillMaxWidth(), color = Color.DarkGray)
                Row(
                    Modifier
                        .align(Alignment.End)
                        .padding(8.dp)) {
                    TextButton(onClick = { onDismiss() }) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = {
                        with(currentProduct) {
                            val billProduct = Product(id, productName, price, stock, quantity)
                            onConfirm(billProduct)
                            quantity = ""
                        }
                    })
                    {
                        Text(text = "Ok")
                    }
                }
            }
        }
    }
}

@Composable
fun MySavePictureDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit,
) {

    var imageName by remember { mutableStateOf("${System.currentTimeMillis()}.jpg") }

    if (show) {

        Dialog(onDismissRequest = { onDismiss() }) {

            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .background(color = Color.White)
            ) {
                MyTitleDialog(title = "Guardar factura" )

                MyTextField( imageName ,"Nombre", KeyboardType.Number) { imageName = it }
                Divider(Modifier.fillMaxWidth(), color = Color.DarkGray)
                Row(
                    Modifier
                        .align(Alignment.End)
                        .padding(8.dp)) {
                    TextButton(onClick = { onDismiss() }) {
                        Text(text = "Cancel")
                    }
                    TextButton(onClick = { onConfirm( imageName ) })
                    {
                        Text(text = "Ok")
                    }
                }
            }
        }
    }
}



@Composable
fun MyTitleDialog(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(24.dp).fillMaxWidth())

}


