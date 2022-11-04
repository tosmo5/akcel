package com.tosmo.akcel.converters.double

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.proxy.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

object DoubleNumberConverter: Converter<Double> {
    
    override val kotlinTypeKey: KClass<Double>
        get() = Double::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.NUMBER
    
    override fun convertToKtData(
        ktCell: KtCell,
        property: ExcelContentProperty
    ): Double {
        return ktCell.numberValue
    }
    
}