package com.tosmo.akcel.converters.float

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object FloatBooleanConverter: Converter<Float> {
    
    private const val ONE = 1f
    private const val ZERO = 0f
    
    override val kotlinTypeKey: KClass<Float>
        get() = Float::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.BOOLEAN
    
    override fun convertToKtData(ktCell: KtCell, property: ExcelContentProperty): Float {
        return if (ktCell.booleaValue) ONE else ZERO
    }
    
}