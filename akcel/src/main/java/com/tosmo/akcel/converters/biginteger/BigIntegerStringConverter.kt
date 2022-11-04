package com.tosmo.akcel.converters.biginteger

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.proxy.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import java.math.BigInteger
import kotlin.reflect.KClass

internal object BigIntegerStringConverter : Converter<BigInteger> {
    
    override val kotlinTypeKey: KClass<BigInteger>
        get() = BigInteger::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.STRING
    
    override fun convertToKtData(
        ktCell: KtCell,
        property: ExcelContentProperty
    ): BigInteger {
        return ktCell.stringValue.toBigInteger()
    }
}