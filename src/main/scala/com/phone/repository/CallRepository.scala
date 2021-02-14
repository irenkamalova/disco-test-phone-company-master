package com.phone.repository

import com.phone.billing.PricedRecord


trait CallRepository {
  def save(record: PricedRecord): Either[Exception, Boolean]

  def findAll(): Seq[PricedRecord]
}
