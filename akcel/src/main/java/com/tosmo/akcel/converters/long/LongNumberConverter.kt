package com.tosmo.akcel.converters.long

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object LongNumberConverter : Converter<Long> {
    
    override val kotlinTypeKey: KClass<Long>
        get() = Long::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.NUMBER
    
    override fun convertToKtData(ktCell: KtCell, property: ExcelContentProperty): Long {
        return ktCell.numberValue.toLong()
    }
    
}