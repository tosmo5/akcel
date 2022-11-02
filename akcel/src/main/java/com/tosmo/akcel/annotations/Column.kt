package com.tosmo.akcel.annotations

import com.tosmo.akcel.converters.AutoConverter
import com.tosmo.akcel.converters.Converter
import kotlin.reflect.KClass

@Target(AnnotationTarget.PROPERTY)
@Retention(AnnotationRetention.RUNTIME)
annotation class Column(
    val name: String,
    /**
     * 所在表格中每条数据中的位置索引，优先级：index > name
     */
    val index: Int = -1,
    
    val readConverter: KClass<out Converter<*>> = AutoConverter::class,
    
    val writeConverter: KClass<out Converter<*>> = AutoConverter::class,
)