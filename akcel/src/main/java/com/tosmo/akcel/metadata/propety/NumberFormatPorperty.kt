package com.tosmo.akcel.metadata.propety

import android.icu.math.BigDecimal
import android.icu.text.DecimalFormat

data class NumberFormatPorperty(
    var format: String,
    /**
     * @see BigDecimal.ROUND_CEILING
     * @see BigDecimal.ROUND_DOWN
     * @see BigDecimal.ROUND_FLOOR
     * @see BigDecimal.ROUND_HALF_DOWN
     * @see BigDecimal.ROUND_HALF_EVEN
     * @see BigDecimal.ROUND_HALF_UP
     * @see BigDecimal.ROUND_UP
     */
    var roundingMode: Int
) {
    fun parseBigDecimal(string: String): BigDecimal {
        return if (format.isEmpty()) {
            BigDecimal(string)
        } else {
            val decimalFormat = DecimalFormat(format)
            decimalFormat.roundingMode = roundingMode
            decimalFormat.isParseBigDecimal = true
            BigDecimal("${decimalFormat.parse(string)}")
        }
    }
    
    fun format(number: Number): String {
        return if (format.isEmpty())
            number.toString()
        else DecimalFormat(format).let {
            it.roundingMode = roundingMode
            it.format(number)
        }
    }
}