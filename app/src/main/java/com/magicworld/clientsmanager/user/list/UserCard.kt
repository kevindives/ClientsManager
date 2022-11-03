package com.magicworld.clientsmanager.user.list

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.magicworld.clientsmanager.model.User
import com.magicworld.clientsmanager.ui.theme.azulClaro
import com.magicworld.clientsmanager.ui.utils.MyCardText

@Composable
fun MyUserCard(user: User, onUserSelected: () -> Unit, onEditSelected: () -> Unit) {
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
            HeaderUserCard { onEditSelected() }
            BodyUserCard(user)
            FooterUserCard(Modifier.align(Alignment.End))
        }

    }
}

@Composable
fun HeaderUserCard(onEditSelected: () -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .background(azulClaro)) {
        Icon(imageVector = Icons.Outlined.Edit,
            contentDescription = "Editar cliente",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .scale(1.2f, 1.2f)
                .padding(end = 12.dp)
                .clickable { onEditSelected() }
        )
    }
}

@Composable
fun BodyUserCard(user: User) {
    Column(modifier = Modifier) {

        MyCardText(user.name, Color.Black)
        MyCardText(user.email, Color.Gray)
        MyCardText(user.phone, Color.Gray)
        MyCardText(user.address, Color.Gray)

    }
}

@Composable
fun FooterUserCard(modifier: Modifier) {

    Box(modifier = modifier
        .fillMaxWidth()
        .height(25.dp))
}




