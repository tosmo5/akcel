package com.tosmo.akcel.adapter

import com.tosmo.akcel.metadata.GlobalConfiguration.autoTrim
import com.tosmo.akcel.metadata.propety.SheetProperty
import org.apache.poi.ss.usermodel.Row
import kotlin.reflect.KClass
import kotlin.reflect.KParameter


class KtRow<T : Any>(private val row: Row, val kClass: KClass<T>) {
    
    val rowIndex: Int = row.rowNum
    
    @Suppress("UNCHECKED_CAST")
    fun createBean(): T {
        val args = mutableMapOf<KParameter, Any>()
        val sheetProp = SheetProperty[kClass]
        row.forEachIndexed { index, cell ->
            val param = sheetProp.paramsMap[index]!!
            val ktCell = KtCell(cell, row.rowNum, index)
            val converter = SheetProperty.allocConverter(param, ktCell)
            val prop = sheetProp.contentProperty[param]!!
            val v = converter.convertToKtData(ktCell, prop)
            args[param] = if (v is String && autoTrim) v.trim() else v
        }
        return sheetProp.constructor.callBy(args) as T
    }
    
    operator fun get(colIx: Int): KtCell {
        return KtCell(row.getCell(colIx), rowIndex, colIx)
    }
}