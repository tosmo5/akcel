package com.tosmo.akcel.adapter

import com.tosmo.akcel.enums.CellDataType
import com.tosmo.akcel.metadata.DataFormatData
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.CellType

data class KtCell(var cell: Cell, var rowIndex: Int, var colIndex: Int) {
    val data: Any? = readRawData(cell)
    
    val type: CellDataType = CellDataType.valueOf(cell.cellType)
    
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
    
    var dataFormatData: DataFormatData? = null
    
    companion object {
        fun readRawData(cell: Cell): Any? {
            return when (cell.cellType) {
                CellType.NUMERIC -> cell.numericCellValue
                CellType.STRING -> cell.stringCellValue
                CellType.FORMULA -> cell.cellFormula
                CellType.BOOLEAN -> cell.booleanCellValue
                CellType.ERROR -> cell.errorCellValue
                else -> null
            }
        }
    }
}
