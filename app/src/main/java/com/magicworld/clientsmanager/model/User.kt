package com.magicworld.clientsmanager.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable
import com.google.gson.annotations.SerializedName

@Parcelize
data class User(
    @SerializedName("id")
    val id:String = "",
    @SerializedName("name")
    val name:String = "",
    @SerializedName("email")
    val email:String = "",
    @SerializedName("phone")
    val phone: String = "",
    @SerializedName("address")
    val address: String = "",
    @SerializedName("set")
    val set: String = ""
):Serializable ,Parcelable
