package com.tosmo.akcel.converters.date

import com.tosmo.akcel.converters.Converter
import java.util.Date
import kotlin.reflect.KClass

internal object DateDateConverter: Converter<Date> {
    
    override val kotlinTypeKey: KClass<Date>
        get() = Date::class
    
}