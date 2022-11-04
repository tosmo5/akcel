package com.tosmo.akcel.converters.bigdecimal

import android.icu.math.BigDecimal
import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.proxy.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object BigDecimalBooleanConverter : Converter<BigDecimal> {
    
    override val kotlinTypeKey: KClass<BigDecimal>
        get() = BigDecimal::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.BOOLEAN
    
    override fun convertToKtData(
        ktCell: KtCell,
        property: ExcelContentProperty
    ): BigDecimal {
        return if (ktCell.booleaValue) BigDecimal.ONE else BigDecimal.ZERO
    }
}