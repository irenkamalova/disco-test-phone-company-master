package com.phone.repository

import com.phone.billing.PricedRecord

class InMemoryCallRepository extends CallRepository {
  var clients = scala.collection.mutable.Map[(String, String), Double]().withDefault(_ => 0L)

  override def save(record: PricedRecord): Either[Exception, Boolean] = {
    val oldSumDuration = clients((record.client, record.phoneNumber))
    val updatedSumDuration = oldSumDuration + record.price
    clients.update((record.client, record.phoneNumber), updatedSumDuration)
    Right(true)
  }

  override def findAll(): Seq[PricedRecord] =
    clients.map { case ((client, phone), price) =>
      PricedRecord(client, phone, price) }.toSeq

}
