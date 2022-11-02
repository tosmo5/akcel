package com.tosmo.akcel.converters.float

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object FloatNumberConverter : Converter<Float> {
    
    override val kotlinTypeKey: KClass<Float>
        get() = Float::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.NUMBER
    
    override fun convertToKtData(ktCell: KtCell, property: ExcelContentProperty): Float {
        return ktCell.numberValue.toFloat()
    }
    
}