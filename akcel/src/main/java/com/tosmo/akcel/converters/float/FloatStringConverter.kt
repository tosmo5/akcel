package com.tosmo.akcel.converters.float

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object FloatStringConverter : Converter<Float> {
    
    override val kotlinTypeKey: KClass<Float>
        get() = Float::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.STRING
    
    override fun convertToKtData(ktCell: KtCell, property: ExcelContentProperty): Float {
        return property.numberFormatProperty?.parseBigDecimal(ktCell.stringValue)?.toFloat()
            ?: ktCell.numberValue.toFloat()
    }
}