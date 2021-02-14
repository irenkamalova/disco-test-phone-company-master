package com.phone.billing

import TariffService.computePrice
import com.phone.billing
import com.phone.repository.Record

object BillingService {

  def createBill(records: Seq[PricedRecord]): Iterable[Bill] = {
    // collect records by client
    records.groupBy(_.client)
      .map { case (client, records) =>
        client -> records.map(r => r.price)
      }
      .map { case (client, prices) =>
        client -> (prices.sum, prices.max)
      }
      .map { case (client, (sumBill, maxBill)) =>
        val finalBill = sumBill - maxBill
        billing.Bill(client, finalBill)
      }
    }
}
