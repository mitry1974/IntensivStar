package ru.androidschool.intensiv.util.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.getYear (): String {
   val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(this)
   return SimpleDateFormat("yyy", Locale.getDefault()).format(date)
}
