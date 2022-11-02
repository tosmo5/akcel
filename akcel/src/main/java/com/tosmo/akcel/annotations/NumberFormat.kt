package com.tosmo.akcel.annotations

import android.icu.math.BigDecimal

annotation class NumberFormat(
    /**
     * @see android.icu.text.DecimalFormat
     */
    val value: String = "",
    
    /**
     * @see BigDecimal.ROUND_CEILING
     * @see BigDecimal.ROUND_DOWN
     * @see BigDecimal.ROUND_FLOOR
     * @see BigDecimal.ROUND_HALF_DOWN
     * @see BigDecimal.ROUND_HALF_EVEN
     * @see BigDecimal.ROUND_HALF_UP
     * @see BigDecimal.ROUND_UP
     */
    val roundingMode: Int = BigDecimal.ROUND_HALF_UP
)
