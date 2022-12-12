package com.magicworld.clientsmanager.main.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.magicworld.clientsmanager.model.Bill

@Composable
fun BillListScreen(currentBill: Bill) {
    Column(Modifier.fillMaxSize()) {
        var totalBruto: Long =0

        Text(text = currentBill.date)
        Text(text = currentBill.client.name)
        Text(text = currentBill.client.email)
        currentBill.listProduct.forEach {
            Text(text = it.productName)
            Text(text = it.quantity)
            totalBruto += it.price.toLong()*it.quantity.toLong()
        }
        Text(text = totalBruto.toString())
        Text(text = (totalBruto * 0.19).toLong().toString())
        Text(text = ((totalBruto * 0.19) + totalBruto).toLong().toString())

    }
}