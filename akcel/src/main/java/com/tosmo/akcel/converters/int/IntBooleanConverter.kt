package com.tosmo.akcel.converters.int

import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object IntBooleanConverter: Converter<Int> {
    override val kotlinTypeKey: KClass<Int>
        get() = Int::class
    override val excelTypeKey: CellDataType
        get() = CellDataType.BOOLEAN
    
    override fun convertToKtData(ktCell: KtCell, property: ExcelContentProperty): Int {
        return if (ktCell.booleaValue) 1 else 0
    }
}