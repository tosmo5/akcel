package com.tosmo.akcel.converters.long

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object LongBooleanConverter: Converter<Long> {
    
    private const val ONE = 1L
    private const val ZERO = 0L
    
    override val kotlinTypeKey: KClass<Long>
        get() = Long::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.BOOLEAN
    
    override fun convertToKtData(ktCell: KtCell, property: ExcelContentProperty): Long {
        return if (ktCell.booleaValue) ONE else ZERO
    }
}