package com.phone

import com.phone.billing.PricedRecord
import com.phone.repository.{InMemoryCallRepository, Record}
import org.scalatest.{FlatSpec, Matchers}

class InMemoryCallRepositoryTest extends FlatSpec with Matchers {
  it should "save and return one record" in {
    val repository = new InMemoryCallRepository
    val record = PricedRecord("B", "555-333-212", 9.0)
    repository.save(record)
    val records = repository.findAll()
    records should have size 1
    records.head shouldBe PricedRecord("B", "555-333-212", 9.0)
  }

  it should "save repeated record and return one record" in {
    val repository = new InMemoryCallRepository
    val record = PricedRecord("B", "555-333-212", 9.0)
    repository.save(record)
    repository.save(record)
    val records = repository.findAll()
    records should have size 1
    records.head shouldBe PricedRecord("B", "555-333-212", 18.0)
  }

  it should "save two different records" in {
    val repository = new InMemoryCallRepository
    repository.save(PricedRecord("B", "555-333-212", 9.0))
    repository.save(PricedRecord("A", "555-333-212", 9.0))
    val records = repository.findAll()
    records should have size 2
    records.head shouldBe PricedRecord("A", "555-333-212", 9.0)
    records(1) shouldBe PricedRecord("B", "555-333-212", 9.0)
  }
}
