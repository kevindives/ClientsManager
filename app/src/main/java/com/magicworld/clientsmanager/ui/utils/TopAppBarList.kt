package com.magicworld.clientsmanager.ui.utils

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.magicworld.clientsmanager.R
import com.magicworld.clientsmanager.ui.theme.aliceAzul

@Composable
fun TopAppBarList( title:String ,onClickDrawer: () -> Unit , onTitleClicked: () -> Unit) {

    TopAppBar(
        title = { ListTitle( title ){ onTitleClicked() } },
        backgroundColor = aliceAzul,
        modifier = Modifier
            .padding(13.dp)
            .clip(RoundedCornerShape(100)),
        navigationIcon = {
            IconButton(onClick = { onClickDrawer() }
            ) {
                Icon(Icons.Outlined.Menu, null, tint = Color.DarkGray)
            }
        },
        actions = {

            IconButton(onClick = { }) {
                Image(
                    painter = painterResource(id = R.drawable.oscurologo),
                    contentDescription = "",
                    modifier = Modifier
                        .clip(CircleShape)
                        .size(45.dp)
                )
            }
        }
    )
}