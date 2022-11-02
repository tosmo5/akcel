package com.tosmo.android_excel

import com.tosmo.akcel.enums.ExcelType
import com.tosmo.akcel.adapter.KtWorkbook
import java.io.InputStream
import kotlin.reflect.KClass

class DemoWorkbook(inputStream: InputStream, type: ExcelType) : KtWorkbook(inputStream, type) {
    override val sheets: List<KClass<*>>
        get() = listOf(SimpleData::class)
}