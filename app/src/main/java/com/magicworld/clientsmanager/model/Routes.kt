package com.magicworld.clientsmanager.model

sealed class Routes(val route:String) {
    object ListScreen:Routes("listscreen")
    object AddScreen:Routes("addscreen")
    object UpdateScreen:Routes("updatescreen")
}