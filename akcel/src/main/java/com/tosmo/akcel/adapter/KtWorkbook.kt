package com.tosmo.akcel.adapter

import com.tosmo.akcel.enums.ExcelType
import com.tosmo.akcel.listener.ReadListener
import com.tosmo.akcel.metadata.propety.SheetProperty
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator
import org.apache.poi.hssf.usermodel.HSSFWorkbook
import org.apache.poi.xssf.usermodel.XSSFFormulaEvaluator
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.InputStream
import kotlin.reflect.KClass

abstract class KtWorkbook(inputStream: InputStream, val type: ExcelType) {
    
    constructor(filePath: String, type: ExcelType = ExcelType.valueOfFileName(filePath)) : this(
        File(filePath), type
    )
    
    constructor(file: File, type: ExcelType = ExcelType.valueOf(file)) : this(
        file.inputStream(), type
    )
    
    abstract val sheets: List<KClass<*>>
    
    private val mWorkbook = when (type) {
        ExcelType.XLS -> HSSFWorkbook(inputStream)
        ExcelType.XLSX -> XSSFWorkbook(inputStream)
        ExcelType.CSV -> throw UnsupportedOperationException()
    }
    
    val evaluator = when (type) {
        ExcelType.XLS -> HSSFFormulaEvaluator(mWorkbook as HSSFWorkbook)
        ExcelType.XLSX -> XSSFFormulaEvaluator(mWorkbook as XSSFWorkbook)
        ExcelType.CSV -> TODO()
    }
    
    private fun assertHasSheet(kClass: KClass<*>) {
        require(kClass in sheets) { "${kClass}不是${this::class}中的内容" }
    }
    
    fun <T : Any> read(kClass: KClass<T>): List<T> {
        val sheet = get(kClass)
        return buildList {
            for (i in sheet.indices) {
                add(sheet[i].createBean())
            }
        }
    }
    
    operator fun <T : Any> get(kClass: KClass<T>): KtSheet<T> {
        assertHasSheet(kClass)
        val prop = SheetProperty.getSheetInfo(kClass)
        return KtSheet(this, mWorkbook.getSheet(prop.sheetName), kClass)
    }
    
    operator fun <T : Any> get(kClass: KClass<T>, rowIx: Int): KtRow<T> {
        return KtRow(get(kClass), kClass, rowIx)
    }
    
    operator fun get(kClass: KClass<*>, rowIx: Int, colIx: Int): KtCell {
        return KtCell(get(kClass, rowIx), colIx)
    }
    
    fun close() {
        mWorkbook.close()
    }
}