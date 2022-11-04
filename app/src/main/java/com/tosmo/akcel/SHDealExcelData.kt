package com.tosmo.akcel

import com.tosmo.akcel.annotations.Sheet

@Sheet(name = "4月", headRowNum = 2, autoTrim = true)
data class SHDealExcelData(
    var actionNo: Int,
    var date: String,
    var companyName: String,
    var shopName: String?,
    var customerName: String,
    var dealType: String,
    var dealName: String,
    var accountPayable: Double,
    var actualPayment: Double,
    var debt: Double,
    var proportion: Double,
    var commission: Double,
    var outstandingCommission: Double,
    var paymentDate: String?,
    var payer: String?,
    var payee: String?,
    var isCorporate: Boolean?,
    var accountType: String?,
    var remark: String?
)
