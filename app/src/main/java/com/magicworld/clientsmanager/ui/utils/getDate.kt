package com.magicworld.clientsmanager.ui.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
fun obtenerFechaConFormato(format: String?, timeZone: String?): String? {
    val calendar: Calendar = Calendar.getInstance()
    val date: Date = calendar.time
    val sdf = SimpleDateFormat(format)
    sdf.timeZone = TimeZone.getTimeZone(timeZone)
    return sdf.format(date)
}

fun getCurrentDate(timeZone: String?): String? {
    val format = "yyyy-MM-dd"
    return obtenerFechaConFormato(format, timeZone)
}

fun getCurrentHour(timeZone: String?): String? {
    val format = "HH:mm:ss"
    return obtenerFechaConFormato(format, timeZone)
}