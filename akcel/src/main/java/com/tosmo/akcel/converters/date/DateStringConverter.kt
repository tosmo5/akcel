package com.tosmo.akcel.converters.date

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import com.tosmo.akcel.util.DateUtils
import java.util.*
import kotlin.reflect.KClass

internal object DateStringConverter : Converter<Date> {
    
    override val kotlinTypeKey: KClass<Date>
        get() = Date::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.STRING
    
    override fun convertToKtData(ktCell: KtCell, property: ExcelContentProperty): Date {
        return DateUtils.parseDate(
            ktCell.stringValue, property.dateTimeFormatProperty?.format ?: ""
        )
    }
    
}