package com.tosmo.android_excel

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import com.tosmo.akcel.annotations.DateTimeFormat
import com.tosmo.akcel.annotations.Sheet

@Sheet("SimpleData", 1)
data class SimpleData(
    val id: Long, val name: String,
    @DateTimeFormat
    val date: String
)
