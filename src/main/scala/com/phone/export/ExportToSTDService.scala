package com.phone.`export`

import com.phone.billing.Bill

object ExportToSTDService extends ExportService {
  override def exportBill(bill: Bill): Unit =
    println(s"Client: ${bill.client}, Bill : ${bill.bill}")
}

