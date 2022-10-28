package com.magicworld.clientsmanager.ui.utils

import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable

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