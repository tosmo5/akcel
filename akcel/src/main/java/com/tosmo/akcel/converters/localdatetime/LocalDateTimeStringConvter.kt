package com.tosmo.akcel.converters.localdatetime

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.metadata.GlobalConfiguration
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import com.tosmo.akcel.util.DateUtils
import java.time.LocalDateTime
import kotlin.reflect.KClass

internal object LocalDateTimeStringConvter : Converter<LocalDateTime> {
    
    override val kotlinTypeKey: KClass<LocalDateTime>
        get() = LocalDateTime::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.STRING
    
    override fun convertToKtData(
        ktCell: KtCell,
        property: ExcelContentProperty
    ): LocalDateTime {
        return DateUtils.parseLocalDateTime(
            ktCell.stringValue, property.dateTimeFormatProperty?.format ?: "",
            GlobalConfiguration.locale
        )
    }
}