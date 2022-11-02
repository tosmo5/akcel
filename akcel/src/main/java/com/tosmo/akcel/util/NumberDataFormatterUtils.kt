package com.tosmo.akcel.util

import android.icu.math.BigDecimal
import com.tosmo.akcel.metadata.GlobalConfiguration
import com.tosmo.akcel.metadata.format.DataFormatter
import java.util.*


object NumberDataFormatterUtils {
    /**
     * Cache DataFormatter.
     */
    private val DATA_FORMATTER_THREAD_LOCAL = ThreadLocal<DataFormatter>()
    
    /**
     * Format number data.
     *
     * @param data
     * @param dataFormatIndex          Not null.
     * @param dataFormatString
     * @param use1904windowing
     * @param locale
     * @param useScientificFormat
     * @return
     */
    fun format(
        data: BigDecimal,
        dataFormatIndex: Int,
        dataFormatString: String,
        use1904windowing: Boolean = GlobalConfiguration.use1904windowing,
        locale: Locale = GlobalConfiguration.locale,
        useScientificFormat: Boolean = GlobalConfiguration.useScientificFormat
    ): String {
        var dataFormatter = DATA_FORMATTER_THREAD_LOCAL.get()
        if (dataFormatter == null) {
            dataFormatter = DataFormatter(use1904windowing, locale, useScientificFormat)
            DATA_FORMATTER_THREAD_LOCAL.set(dataFormatter)
        }
        return dataFormatter.format(data, dataFormatIndex, dataFormatString)
    }
    
    fun removeThreadLocalCache() {
        DATA_FORMATTER_THREAD_LOCAL.remove()
    }
}
