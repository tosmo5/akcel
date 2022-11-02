package com.tosmo.akcel.converters.double

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object DoubleBooleanConverter: Converter<Double> {
    
    private const val ONE = 1.0
    private const val ZERE = 0.0
    
    override val kotlinTypeKey: KClass<Double>
        get() = Double::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.BOOLEAN
    
    override fun convertToKtData(
        ktCell: KtCell,
        property: ExcelContentProperty
    ): Double {
        return if (ktCell.booleaValue) ONE else ZERE
    }
    
}