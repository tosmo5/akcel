package com.tosmo.akcel.converters.double

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object DoubleStringConverter : Converter<Double> {
    
    override val kotlinTypeKey: KClass<Double>
        get() = Double::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.STRING
    
    override fun convertToKtData(
        ktCell: KtCell,
        property: ExcelContentProperty
    ): Double {
        return property.numberFormatProperty?.parseBigDecimal(ktCell.stringValue)?.toDouble()
            ?: ktCell.numberValue
    }
    
}