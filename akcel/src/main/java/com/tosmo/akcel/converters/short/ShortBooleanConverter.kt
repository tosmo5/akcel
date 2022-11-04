package com.tosmo.akcel.converters.short

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.proxy.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object ShortBooleanConverter : Converter<Short> {
    
    private const val ONE = 1.toShort()
    private const val ZERO = 0.toShort()
    
    override val kotlinTypeKey: KClass<Short>
        get() = Short::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.BOOLEAN
    
    override fun convertToKtData(ktCell: KtCell, property: ExcelContentProperty): Short {
        return if (ktCell.booleaValue) ONE else ZERO
    }
}