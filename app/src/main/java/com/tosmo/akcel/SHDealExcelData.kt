package com.tosmo.akcel

import com.tosmo.akcel.annotations.Sheet
import java.util.*

@Sheet(name = "4月", headRowNum = 2, autoTrim = true)
data class SHDealExcelData(
    var actionNo: Int,
    var date: String,
    var companyName: String,
    var shopName: String = "",
    var customerName: String,
    var dealType: String,
    var dealName: String,
    var accountPayable: Double = 0.0,
    var actualPayment: Double = 0.0,
    var debt: Double = 0.0,
    var proportion: Double = 0.0,
    var commission: Double = 0.0,
    var outstandingCommission: Double = 0.0,
    var paymentDate: Date = Date(),
    var payer: String = "",
    var payee: String = "",
    var isCorporate: Boolean = false,
    var accountType: String = "",
    var remark: String?
)
