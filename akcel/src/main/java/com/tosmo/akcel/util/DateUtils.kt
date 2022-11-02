package com.tosmo.akcel.util

import android.icu.text.DateFormat
import android.icu.text.SimpleDateFormat
import androidx.core.net.ParseException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.regex.Pattern

object DateUtils {
    /**
     * Is a cache of dates
     */
    private val DATE_THREAD_LOCAL = ThreadLocal<MutableMap<Int, Boolean>>()
    
    /**
     * Is a cache of dates
     */
    private val DATE_FORMAT_THREAD_LOCAL: ThreadLocal<MutableMap<String, DateFormat>> =
        ThreadLocal<MutableMap<String, DateFormat>>()
    
    /**
     * The following patterns are used in [.isADateFormat]
     */
    private val date_ptrn1: Pattern = Pattern.compile("^\\[\\$-.*?]")
    private val date_ptrn2: Pattern = Pattern.compile("^\\[[a-zA-Z]+]")
    private val date_ptrn3a: Pattern = Pattern.compile("[yYmMdDhHsS]")
    
    // add "\u5e74 \u6708 \u65e5" for Chinese/Japanese date format:2017 \u5e74 2 \u6708 7 \u65e5
    private val date_ptrn3b: Pattern =
        Pattern.compile("^[\\[\\]yYmMdDhHsS\\-T/\u5e74\u6708\u65e5,. :\"\\\\]+0*[ampAMP/]*$")
    
    // elapsed time patterns: [h],[m] and [s]
    private val date_ptrn4: Pattern = Pattern.compile("^\\[([hH]+|[mM]+|[sS]+)]")
    
    // for format which start with "[DBNum1]" or "[DBNum2]" or "[DBNum3]" could be a Chinese date
    private val date_ptrn5: Pattern = Pattern.compile("^\\[DBNum([123])]")
    
    // for format which start with "年" or "月" or "日" or "时" or "分" or "秒" could be a Chinese date
    private val date_ptrn6: Pattern = Pattern.compile("([年月日时分秒])+")
    const val DATE_FORMAT_10 = "yyyy-MM-dd"
    const val DATE_FORMAT_14 = "yyyyMMddHHmmss"
    const val DATE_FORMAT_16 = "yyyy-MM-dd HH:mm"
    const val DATE_FORMAT_16_FORWARD_SLASH = "yyyy/MM/dd HH:mm"
    const val DATE_FORMAT_17 = "yyyyMMdd HH:mm:ss"
    const val DATE_FORMAT_19 = "yyyy-MM-dd HH:mm:ss"
    const val DATE_FORMAT_19_FORWARD_SLASH = "yyyy/MM/dd HH:mm:ss"
    private const val MINUS = "-"
    var defaultDateFormat = DATE_FORMAT_19
    
    /**
     * convert string to date
     *
     * @param dateString
     * @param dateFormat
     * @return
     * @throws ParseException
     */
    @Throws(ParseException::class)
    fun parseDate(dateString: String, dateFormat: String): Date {
        var df = dateFormat
        if (df.isEmpty()) {
            df = switchDateFormat(dateString)
        }
        return getCacheDateFormat(df).parse(dateString)
    }
    
    /**
     * convert string to date
     *
     * @param dateString
     * @param dateFormat
     * @param local
     * @return
     */
    fun parseLocalDateTime(dateString: String, dateFormat: String, local: Locale?): LocalDateTime {
        var df = dateFormat
        if (df.isEmpty()) {
            df = switchDateFormat(dateString)
        }
        return local?.let {
            LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(df, it))
        } ?: LocalDateTime.parse(dateString, DateTimeFormatter.ofPattern(df))
    }
    
    /**
     * convert string to date
     *
     * @param dateString
     * @return
     * @throws ParseException
     */
    @Throws(ParseException::class)
    fun parseDate(dateString: String): Date {
        return parseDate(dateString, switchDateFormat(dateString))
    }
    
    /**
     * switch date format
     *
     * @param dateString
     * @return
     */
    fun switchDateFormat(dateString: String): String {
        return when (dateString.length) {
            19 -> if (dateString.contains(MINUS)) {
                DATE_FORMAT_19
            } else {
                DATE_FORMAT_19_FORWARD_SLASH
            }
            16 -> if (dateString.contains(MINUS)) {
                DATE_FORMAT_16
            } else {
                DATE_FORMAT_16_FORWARD_SLASH
            }
            17 -> DATE_FORMAT_17
            14 -> DATE_FORMAT_14
            10 -> DATE_FORMAT_10
            else -> throw IllegalArgumentException(
                "can not find date format for：$dateString"
            )
        }
    }
    
    /**
     * Format date
     *
     *
     * yyyy-MM-dd HH:mm:ss
     *
     * @param date
     * @return
     */
    fun format(date: Date): String {
        return format(date)
    }
    
    /**
     * Format date
     *
     * @param date
     * @param dateFormat
     * @return
     */
    fun format(date: Date, dateFormat: String = ""): String {
        var df = dateFormat
        if (df.isEmpty()) {
            df = defaultDateFormat
        }
        return getCacheDateFormat(df).format(date)
    }
    
    /**
     * Format date
     *
     * @param date
     * @param dateFormat
     * @return
     */
    fun format(date: LocalDateTime, dateFormat: String, local: Locale?): String? {
        var df = dateFormat
        if (df.isEmpty()) {
            df = defaultDateFormat
        }
        return local?.let {
            date.format(DateTimeFormatter.ofPattern(df, it))
        } ?: date.format(DateTimeFormatter.ofPattern(df))
    }
    
    private fun getCacheDateFormat(dateFormat: String): DateFormat {
        var dateFormatMap: MutableMap<String, DateFormat>? = DATE_FORMAT_THREAD_LOCAL.get()
        if (dateFormatMap == null) {
            dateFormatMap = mutableMapOf()
            DATE_FORMAT_THREAD_LOCAL.set(dateFormatMap)
        }
        return dateFormatMap.getOrPut(dateFormat) {
            SimpleDateFormat.getPatternInstance(dateFormat)
        }
    }
    
    /**
     * Determine if it is a date format.
     *
     * @param formatIndex
     * @param formatString
     * @return
     */
    fun isADateFormat(formatIndex: Int, formatString: String): Boolean {
        var isDateCache = DATE_THREAD_LOCAL.get()
        if (isDateCache == null) {
            isDateCache = mutableMapOf()
            DATE_THREAD_LOCAL.set(isDateCache)
        } else {
            isDateCache.putIfAbsent(formatIndex, isADateFormatUncached(formatIndex, formatString))
        }
        return isDateCache[formatIndex]!!
    }
    
    /**
     * Determine if it is a date format.
     *
     * @param formatIndex
     * @param formatString
     * @return
     */
    fun isADateFormatUncached(formatIndex: Int, formatString: String): Boolean {
        // First up, is this an internal date format?
        if (isInternalDateFormat(formatIndex)) {
            return true
        }
        if (formatString.isEmpty()) {
            return false
        }
        var fs = formatString
        val length = fs.length
        val sb = StringBuilder(length)
        var i = 0
        while (i < length) {
            val c = fs[i]
            if (i < length - 1) {
                val nc = fs[i + 1]
                if (c == '\\') {
                    when (nc) {
                        '-', ',', '.', ' ', '\\' -> {
                            i++
                            // skip current '\' and continue to the next char
                            continue
                        }
                    }
                } else if (c == ';' && nc == '@') {
                    i++
                    i++
                    // skip ";@" duplets
                    continue
                }
            }
            sb.append(c)
            i++
        }
        fs = sb.toString()
        
        // short-circuit if it indicates elapsed time: [h], [m] or [s]
        if (date_ptrn4.matcher(fs).matches()) {
            return true
        }
        // If it starts with [DBNum1] or [DBNum2] or [DBNum3]
        // then it could be a Chinese date
        fs = date_ptrn5.matcher(fs).replaceAll("")
        // If it starts with [$-...], then could be a date, but
        // who knows what that starting bit is all about
        fs = date_ptrn1.matcher(fs).replaceAll("")
        // If it starts with something like [Black] or [Yellow],
        // then it could be a date
        fs = date_ptrn2.matcher(fs).replaceAll("")
        // You're allowed something like dd/mm/yy;[red]dd/mm/yy
        // which would place dates before 1900/1904 in red
        // For now, only consider the first one
        val separatorIndex = fs.indexOf(';')
        if (0 < separatorIndex && separatorIndex < fs.length - 1) {
            fs = fs.substring(0, separatorIndex)
        }
        
        // Ensure it has some date letters in it
        // (Avoids false positives on the rest of pattern 3)
        if (!date_ptrn3a.matcher(fs).find()) {
            return false
        }
        
        // If we get here, check it's only made up, in any case, of:
        // y m d h s - \ / , . : [ ] T
        // optionally followed by AM/PM
        var result: Boolean = date_ptrn3b.matcher(fs).matches()
        if (result) {
            return true
        }
        result = date_ptrn6.matcher(fs).find()
        return result
    }
    
    /**
     * Given a format ID this will check whether the format represents an internal excel date format or not.
     *
     * @see .isADateFormat
     */
    fun isInternalDateFormat(format: Int): Boolean {
        return when (format) {
            0x0e, 0x0f, 0x10, 0x11, 0x12, 0x13, 0x14, 0x15, 0x16, 0x1b, 0x1c, 0x1d, 0x1e, 0x1f, 0x20, 0x21, 0x22, 0x23, 0x24, 0x2d, 0x2e, 0x2f, 0x32, 0x33, 0x34, 0x35, 0x36, 0x37, 0x38, 0x39, 0x3a -> true
            else -> false
        }
    }
    
    fun removeThreadLocalCache() {
        DATE_THREAD_LOCAL.remove()
        DATE_FORMAT_THREAD_LOCAL.remove()
    }
}
