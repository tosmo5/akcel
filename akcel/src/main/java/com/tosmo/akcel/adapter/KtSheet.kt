package com.tosmo.akcel.adapter

import com.tosmo.akcel.metadata.propety.SheetProperty
import org.apache.poi.ss.usermodel.Sheet
import kotlin.reflect.KClass

class KtSheet<T : Any>(val sheet: Sheet, val kClass: KClass<T>) {
    
    val indices: IntRange
        get() = SheetProperty[kClass].headRowNum..sheet.lastRowNum
    
    operator fun get(rowIx: Int): KtRow<T> {
        return KtRow(sheet.getRow(rowIx), kClass)
    }
    
    operator fun get(rowIx: Int, colIx: Int): KtCell {
        return KtCell(sheet.getRow(rowIx).getCell(colIx), rowIx, colIx)
    }
}