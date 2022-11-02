package com.tosmo.akcel.converters.byte

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object ByteStringConverter : Converter<Byte> {
    
    override val kotlinTypeKey: KClass<Byte>
        get() = Byte::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.STRING
    
    override fun convertToKtData(ktCell: KtCell, property: ExcelContentProperty): Byte {
        return property.numberFormatProperty?.parseBigDecimal(ktCell.stringValue)?.toByte()
            ?: ktCell.numberValue.toInt().toByte()
    }
    
}