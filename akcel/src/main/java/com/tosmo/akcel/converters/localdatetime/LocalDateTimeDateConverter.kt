package com.tosmo.akcel.converters.localdatetime

import com.tosmo.akcel.converters.Converter
import com.tosmo.akcel.enums.CellDataType
import java.time.LocalDateTime
import kotlin.reflect.KClass

internal object LocalDateTimeDateConverter: Converter<LocalDateTime> {
    
    override val kotlinTypeKey: KClass<LocalDateTime>
        get() = LocalDateTime::class
    
    override val excelTypeKey: CellDataType
        get() = CellDataType.DATE
}