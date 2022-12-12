package com.magicworld.clientsmanager.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Bill(
    val id :String,
    val date: String,
    val client: User,
    val listProduct: List<Product>
):Parcelable
