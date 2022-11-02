package com.tosmo.akcel.annotations

import kotlin.reflect.KClass

/**
 *
 */
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class Workbook(
    val sheets: Array<KClass<*>>
)
