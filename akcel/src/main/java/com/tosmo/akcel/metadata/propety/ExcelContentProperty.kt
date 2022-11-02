package com.tosmo.akcel.metadata.propety

data class ExcelContentProperty(
    /**
     * 日期格式
     */
    val dateTimeFormatProperty: DateTimeFormatProperty? = null,
    /**
     * 数字格式
     */
    val numberFormatProperty: NumberFormatPorperty? = null
)