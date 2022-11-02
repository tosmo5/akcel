package com.tosmo.akcel.converters.biginteger

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import java.math.BigInteger
import kotlin.reflect.KClass

internal object BigIntegerNumberConverter : Converter<BigInteger> {
    
    override val kotlinTypeKey: KClass<BigInteger>
        get() = BigInteger::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.NUMBER
    
    override fun convertToKtData(
        ktCell: KtCell,
        property: ExcelContentProperty
    ): BigInteger {
        return ktCell.stringValue.toBigInteger()
    }
}