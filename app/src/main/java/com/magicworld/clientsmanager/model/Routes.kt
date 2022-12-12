package com.magicworld.clientsmanager.model

sealed class Routes(val route:String) {
    object UserListScreen:Routes("userlistscreen")
    object UserAddScreen:Routes("useraddscreen")
    object UserUpdateScreen:Routes("userupdatescreen")
    object ProductListScreen:Routes( "productlistscreen")
    object ProductAddScreen:Routes( "productaddscreen")
    object ProductUpdateScreen:Routes( "productupdatescreen")
    object BillAddScreen:Routes( "billaddscreen")
    object BillListScreen:Routes( "billListscreen")
}