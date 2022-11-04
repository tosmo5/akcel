package com.tosmo.akcel.converters.short

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.proxy.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object ShortNumberConverter : Converter<Short> {
    
    override val kotlinTypeKey: KClass<Short>
        get() = Short::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.NUMBER
    
    override fun convertToKtData(ktCell: KtCell, property: ExcelContentProperty): Short {
        return ktCell.numberValue.toInt().toShort()
    }
}