package com.tosmo.akcel.converters.bigdecimal

import android.icu.math.BigDecimal
import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.proxy.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object BigDecimalStringConverter : Converter<BigDecimal> {
    
    override val kotlinTypeKey: KClass<BigDecimal>
        get() = BigDecimal::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.STRING
    
    override fun convertToKtData(
        ktCell: KtCell,
        property: ExcelContentProperty
    ): BigDecimal {
        return property.numberFormatProperty?.parseBigDecimal(ktCell.stringValue)
            ?: BigDecimal(ktCell.stringValue)
    }
}