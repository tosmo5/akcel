package com.tosmo.akcel

import com.tosmo.akcel.proxy.KtWorkbook
import com.tosmo.akcel.enums.ExcelType
import java.io.InputStream
import kotlin.reflect.KClass

class DemoWorkbook(inputStream: InputStream, type: ExcelType) : KtWorkbook(inputStream, type) {
    override val entities: List<KClass<*>>
        get() = listOf(SimpleData::class)
}