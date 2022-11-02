package com.tosmo.akcel.converters.string

import android.icu.math.BigDecimal
import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import com.tosmo.akcel.util.DateUtils
import com.tosmo.akcel.util.NumberDataFormatterUtils
import org.apache.poi.ss.usermodel.DateUtil
import kotlin.reflect.KClass

internal object StringNumberConverter : Converter<String> {
    
    override val kotlinTypeKey: KClass<String>
        get() = String::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.NUMBER
    
    override fun convertToKtData(
        ktCell: KtCell,
        property: ExcelContentProperty
    ): String {
        val value = ktCell.numberValue
        // 读取日期
        property.dateTimeFormatProperty?.let {
            return DateUtils.format(
                DateUtil.getJavaDate(value, it.use1904windowing, null), it.format
            )
        }
        // 读取数字
        property.numberFormatProperty?.let {
            return it.format(value)
        }
        // Excel中定义的格式
        ktCell.dataFormatData?.let {
            if (it.format.isNotEmpty()) {
                return NumberDataFormatterUtils.format(BigDecimal(value), it.index, it.format)
            }
        }
        // 默认转换为数字
        return value.toString()
    }
}