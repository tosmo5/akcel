package com.tosmo.akcel.converters.string

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object StringErrorConverter : Converter<String> {
    
    override val kotlinTypeKey: KClass<String>
        get() = String::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.ERROR
    
    override fun convertToKtData(
        ktCell: KtCell,
        property: ExcelContentProperty
    ): String {
        return ktCell.stringValue
    }
}