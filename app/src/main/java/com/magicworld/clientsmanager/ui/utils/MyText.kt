package com.magicworld.clientsmanager.ui.utils

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListTitle( title: String , onTitleClicked: () -> Unit) {
    Text(text = title , color = Color.DarkGray, fontSize = 15.sp , modifier = Modifier.clickable { onTitleClicked() })
}

@Composable
fun MyCardText(text: String, color: Color) {
    Text(text = text,
        color = color,
        fontSize = 20.sp,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))
}

@Composable
fun MyTextInfoBill(text: String, fontSize: Int, color: Color = Color.Black) {
    Text(text = text,
        fontSize = fontSize.sp,
        color = color,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth())
}