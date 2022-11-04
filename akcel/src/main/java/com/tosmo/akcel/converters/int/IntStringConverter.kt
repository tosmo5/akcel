package com.tosmo.akcel.converters.int

import com.tosmo.akcel.proxy.KtCell
import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object IntStringConverter : Converter<Int> {
    override val kotlinTypeKey: KClass<Int>
        get() = Int::class
    override val excelTypeKey: CellDataType
        get() = CellDataType.STRING
    
    override fun convertToKtData(ktCell: KtCell, property: ExcelContentProperty): Int {
        return ktCell.numberValue.toInt()
    }
}