package com.tosmo.akcel.adapter

import android.util.Log
import com.tosmo.akcel.metadata.GlobalConfiguration.autoTrim
import com.tosmo.akcel.metadata.propety.SheetProperty
import org.apache.poi.ss.usermodel.Row
import kotlin.reflect.KClass
import kotlin.reflect.KParameter


class KtRow<T : Any> internal constructor(
    val ktSheet: KtSheet<T>,
    val kClass: KClass<T>,
    val rowIndex: Int,
) {
    internal val row: Row = ktSheet.sheet.getRow(rowIndex)
    
    @Suppress("UNCHECKED_CAST")
    fun createBean(): T {
        val args = mutableMapOf<KParameter, Any?>()
        val sheetProp = SheetProperty[kClass]
        sheetProp.paramsMap.forEach { (index, param) ->
            val ktCell = KtCell(this, index)
            if (ktCell.data != null) { // 从excel取到的值不为空时
                val converter = SheetProperty.allocConverter(param, ktCell)
                val prop = sheetProp.contentProperty[param]!!
                converter?.convertToKtData(ktCell, prop)?.let {
                    args[param] = if (it is String && autoTrim) it.trim() else it
                } ?: kotlin.run {
                    // 当没有转换器时，需要有默认值
                    require(param.isOptional) { "${param.name}没有转换器又没有默认值" }
                    Log.d("akcel", "${param.name}找不到转换器，使用默认值")
                }
            } else { // 从excel取到空值时
                // 需要参数有默认值或可为空
                require(param.isOptional || param.type.isMarkedNullable) {
                    "${param.name}不可为空又没有默认值，但取到了空值"
                }
                if (!param.isOptional) {
                    args[param] = null
                }
            }
        }
        return sheetProp.constructor.callBy(args) as T
    }
    
    operator fun get(colIx: Int): KtCell {
        return KtCell(this, colIx)
    }
}