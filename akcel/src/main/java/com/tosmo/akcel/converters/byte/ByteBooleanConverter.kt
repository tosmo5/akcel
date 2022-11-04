package com.tosmo.akcel.converters.byte

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.proxy.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

internal object ByteBooleanConverter : Converter<Byte> {
    
    private const val ONE: Byte = 1
    private const val ZERO: Byte = 0
    
    override val kotlinTypeKey: KClass<Byte>
        get() = Byte::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.BOOLEAN
    
    override fun convertToKtData(ktCell: KtCell, property: ExcelContentProperty): Byte {
        return if (ktCell.booleaValue) ONE else ZERO
    }
    
}