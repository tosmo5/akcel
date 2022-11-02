package com.tosmo.akcel.adapter

import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.metadata.DataFormatData
import org.apache.poi.ss.usermodel.CellType

data class KtCell internal constructor(var ktRow: KtRow<*>, val colIndex: Int) {
    
    val rowIndex: Int = ktRow.rowIndex
    
    internal val cell = ktRow.row.getCell(colIndex) ?: ktRow.row.createCell(colIndex)
    
    val data: Any?
        get() {
            val value = ktRow.ktSheet.workbook.evaluator.evaluate(cell)
            return value?.let {
                when (it.cellType) {
                    CellType.NUMERIC -> value.numberValue
                    CellType.STRING -> value.stringValue
                    CellType.BOOLEAN -> value.booleanValue
                    CellType.ERROR -> value.errorValue
                    else -> null
                }
            }
        }
    
    val type: CellDataType = CellDataType.valueOf(cell)
    
    /**
     * @see CellDataType.NUMBER
     */
    val numberValue: Double
        get() = data.toString().toDouble()
    
    /**
     * @see CellDataType.STRING
     * @see CellDataType.ERROR
     */
    val stringValue: String
        get() = data.toString()
    
    /**
     * @see CellDataType.BOOLEAN
     */
    val booleaValue: Boolean
        get() = data as Boolean
    
    /**
     * 公式
     */
    var formula: String?
        get() = if (cell.cellType == CellType.FORMULA) {
            cell.cellFormula
        } else null
        set(value) {
            cell.cellFormula = value
        }
    
    var dataFormatData: DataFormatData? = null
}
