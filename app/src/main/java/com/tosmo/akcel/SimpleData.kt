package com.tosmo.akcel

import com.tosmo.akcel.annotations.DateTimeFormat
import com.tosmo.akcel.annotations.Sheet

@Sheet("SimpleData", 1)
data class SimpleData(
    val id: Long, val name: String,
    @DateTimeFormat
    val date: String
)
