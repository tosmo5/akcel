package com.tosmo.akcel.converters.string

import com.tosmo.akcel.converters.Converter
import kotlin.reflect.KClass

internal object StringImageConverter: Converter<String> {
    
    override val kotlinTypeKey: KClass<String>
        get() = String::class
}