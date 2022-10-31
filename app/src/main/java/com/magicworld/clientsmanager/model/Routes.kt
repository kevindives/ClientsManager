package com.magicworld.clientsmanager.model

sealed class Routes(val route:String) {
    object UserListScreen:Routes("userlistscreen")
    object UserAddScreen:Routes("useraddscreen")
    object UserUpdateScreen:Routes("userupdatescreen")
}