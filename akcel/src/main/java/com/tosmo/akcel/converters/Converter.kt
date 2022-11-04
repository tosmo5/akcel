package com.tosmo.akcel.converters

import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.proxy.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import kotlin.reflect.KClass

interface Converter<T : Any> {
    val kotlinTypeKey: KClass<T>
        get() {
            throw UnsupportedOperationException("不支持的操作")
        }
    
    val excelTypeKey: CellDataType
        get() {
            throw UnsupportedOperationException("不支持的操作")
        }
    
    fun convertToKtData(ktCell: KtCell, property: ExcelContentProperty): T {
        throw UnsupportedOperationException("不支持的操作")
    }
}
