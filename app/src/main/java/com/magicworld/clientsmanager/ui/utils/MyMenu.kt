package com.magicworld.clientsmanager.ui.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Inventory
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun MyDrawer(
    onUserListSelected: () -> Unit,
    onProductListSelected: () -> Unit,
) {

    Column(Modifier.padding(8.dp)) {

        MyTitleMenu("Clients Manager")
        MenuItem(title = "Clientes",  icon = Icons.Outlined.Person) {
            onUserListSelected()
        }
        MenuItem(title = "Productos", icon = Icons.Outlined.Inventory) {
            onProductListSelected()
        }

    }
}

@Composable
fun MyTitleMenu(title: String) {
    Row(Modifier.fillMaxWidth()) {
        Text(
            text = title, modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp, horizontal = 13.dp)
                .align(Alignment.CenterVertically),
            color = Color.DarkGray,
            fontSize = 30.sp
        )
    }

}

@Composable
fun MenuItem(
    title: String,
    icon: ImageVector,
    onItemClicked: () -> Unit,
) {
    var colorBackground by remember { mutableStateOf(Color.White) }
    var colorContent by remember { mutableStateOf(Color.DarkGray) }

    IconButton(
        onClick = { onItemClicked() },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(100))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorBackground)
                .size(60.dp)
                .clip(RoundedCornerShape(100))
                .clickable {
                    colorBackground = Color(0xFFE0FFFF)
                    colorContent = Color(0xFF1E90FF)
                    onItemClicked()
                }
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = colorContent,
                modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .size(30.dp)
                    .align(Alignment.CenterVertically)
            )
            Text(
                text = title,
                fontSize = 19.sp,
                color = colorContent,
                modifier = Modifier
                    .weight(1f)
                    .align(Alignment.CenterVertically)
            )
        }
    }

}