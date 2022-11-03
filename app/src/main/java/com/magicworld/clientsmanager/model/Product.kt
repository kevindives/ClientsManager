package com.magicworld.clientsmanager.model

import android.os.Parcelable
import java.io.Serializable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("productName")
    val productName: String = "",
    @SerializedName("price")
    val price: String = "",
    @SerializedName("stock")
    val stock: String = "",
    @SerializedName( "quantity")
    val quantity: String = "",
):Parcelable,Serializable
