package com.tosmo.akcel.converters.date

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.proxy.KtCell
import com.tosmo.akcel.metadata.GlobalConfiguration
import com.tosmo.akcel.metadata.propety.ExcelContentProperty
import org.apache.poi.ss.usermodel.DateUtil
import java.util.*
import kotlin.reflect.KClass

internal object DateNumberConverter : Converter<Date> {
    
    override val kotlinTypeKey: KClass<Date>
        get() = Date::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.NUMBER
    
    override fun convertToKtData(ktCell: KtCell, property: ExcelContentProperty): Date {
        return DateUtil.getJavaDate(
            ktCell.numberValue,
            property.dateTimeFormatProperty?.use1904windowing
                ?: GlobalConfiguration.use1904windowing,
            null
        )
    }
    
}