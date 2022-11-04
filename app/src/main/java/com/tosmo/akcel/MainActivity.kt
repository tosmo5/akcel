package com.tosmo.akcel

import android.Manifest
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.tosmo.akcel.enums.ExcelType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val r = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            if (it.values.any { v -> !v }) {
                Log.d("APP", "读写权限已申请失败")
            } else {
                Log.d("APP", "读写权限已申请成功")
                
                val inputStream = assets.open("2022美蝶对外活动流水.xlsx")
                val book = SHDealWorkbook(inputStream, ExcelType.XLSX)
                book.read(SHDealExcelData::class).forEach {
                    Log.d("APP", it.toString())
                }
            }
        }
        
        r.launch(
            arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
        )
    }
}