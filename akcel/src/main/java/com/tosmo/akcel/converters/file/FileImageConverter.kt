package com.tosmo.akcel.converters.file

import com.tosmo.akcel.converters.Converter
import java.io.File
import kotlin.reflect.KClass

internal object FileImageConverter: Converter<File> {
    
    override val kotlinTypeKey: KClass<File>
        get() = File::class
}