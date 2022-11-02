package com.tosmo.akcel.converters.boolean

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object BooleanNumberConverter : Converter<Boolean> {
    override val kotlinTypeKey: KClass<Boolean>
        get() = Boolean::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.NUMBER
    
    override fun convertToKtData(
        ktCell: KtCell,
        property: ExcelContentProperty
    ): Boolean {
        return when (ktCell.numberValue) {
            1.0 -> true
            else -> false
        }
    }
}