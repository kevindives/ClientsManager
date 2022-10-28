package com.magicworld.clientsmanager.ui.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.magicworld.clientsmanager.model.User
import com.magicworld.clientsmanager.ui.theme.azulClaro

@Composable
fun MyCard(user: User, onUserSelected: () -> Unit , onEditSelected:()-> Unit ) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onUserSelected() },
        border = BorderStroke(0.5.dp, Color.LightGray),
        shape = RoundedCornerShape(8),
        elevation = 4.dp
    ) {
        Column {
            HeaderCard{ onEditSelected()}
            BodyCard(user)
            FooterCard(Modifier.align(Alignment.End))
        }

    }
}

@Composable
fun HeaderCard(onEditSelected: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(azulClaro)) {
        Icon(imageVector = Icons.Outlined.Edit,
            contentDescription = "Editar cliente",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .scale(1.2f ,1.2f)
                .padding(end = 12.dp)
                .clickable { onEditSelected() }
        )
    }
}

@Composable
fun BodyCard(user: User) {
    Column(modifier = Modifier) {

        MyText(user.name, Color.Black)
        MyText(user.email, Color.Gray)
        MyText(user.phone, Color.Gray)
        MyText(user.address, Color.Gray)

    }
}

@Composable
fun MyText(text: String, color: Color) {
    Text(text = text,
        color = color,
        fontSize = 20.sp,
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp))
}

@Composable
fun FooterCard(modifier: Modifier) {

    Box(modifier = modifier
        .fillMaxWidth()
        .height(25.dp))
}




