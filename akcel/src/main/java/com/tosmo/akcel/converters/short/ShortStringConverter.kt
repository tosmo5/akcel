package com.tosmo.akcel.converters.short

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object ShortStringConverter : Converter<Short> {
    
    override val kotlinTypeKey: KClass<Short>
        get() = Short::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.STRING
    
    
    override fun convertToKtData(ktCell: KtCell, property: ExcelContentProperty): Short {
        return property.numberFormatProperty?.parseBigDecimal(ktCell.stringValue)?.toShort()
            ?: ktCell.numberValue.toInt().toShort()
    }
    
}