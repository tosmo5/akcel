package com.tosmo.akcel.annotations

/**
 * 日期时间格式，默认优先级：DateTimeFormat > NumberFormat
 *
 * @author Thomas Miao
 * @see com.tosmo.akcel.converters.string.StringNumberConverter.convertToKtData
 */
annotation class DateTimeFormat(
    /**
     * 日期时间格式
     * @see android.icu.text.DateFormat
     */
    val parttern: String = "",
    val use1904windowing: Boolean = false
)
