package com.tosmo.akcel.converters.bytearray

import com.tosmo.akcel.converters.Converter
import kotlin.reflect.KClass

internal object BoxingByteArrayImageConverter : Converter<ByteArray> {
    
    override val kotlinTypeKey: KClass<ByteArray>
        get() = ByteArray::class
    
}