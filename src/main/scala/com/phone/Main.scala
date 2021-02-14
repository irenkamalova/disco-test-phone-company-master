package com.phone

import com.phone.billing.PricedRecord
import com.phone.billing.TariffService.computePrice
import com.phone.config.ApplicationConfig
import com.phone.repository.{InMemoryCallRepository, Record}

object Main extends App {

  import com.phone.parser.Parser._
  import com.phone.billing.BillingService._
  import com.phone.`export`.ExportToSTDService._

  def start(): Unit = {
    val repository = new InMemoryCallRepository
    val f: Record => Either[Exception, Boolean] =
      record => {
        val pricedRecord = PricedRecord(record.client, record.phoneNumber, computePrice(record.duration))
        repository.save(pricedRecord)
      }
    parse(ApplicationConfig.fileName)(f)
    val records = repository.findAll()
    createBill(records).foreach(exportBill)
  }

  start()
}
