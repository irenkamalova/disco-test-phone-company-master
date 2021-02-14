package com.phone.`export`

import com.phone.billing.Bill

trait ExportService {
  def exportBill(bill: Bill): Unit
}
