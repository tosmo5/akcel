package com.tosmo.akcel.converters.localdatetime

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.adapter.KtCell
import com.tosmo.akcel.metadata.GlobalConfiguration
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import org.apache.poi.ss.usermodel.DateUtil
import java.time.LocalDateTime
import kotlin.reflect.KClass

internal object LocalDateTimeNumberConverter : Converter<LocalDateTime> {
    
    override val kotlinTypeKey: KClass<LocalDateTime>
        get() = LocalDateTime::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.NUMBER
    
    override fun convertToKtData(
        ktCell: KtCell, property: ExcelContentProperty
    ): LocalDateTime {
        return DateUtil.getLocalDateTime(
            ktCell.numberValue, property.dateTimeFormatProperty?.use1904windowing
                ?: GlobalConfiguration.use1904windowing
        )
    }
}