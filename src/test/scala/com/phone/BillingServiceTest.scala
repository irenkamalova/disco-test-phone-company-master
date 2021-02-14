package com.phone

import com.phone.billing.{Bill, PricedRecord}
import org.scalatest.{FlatSpec, Matchers}
import com.phone.billing.BillingService._
import com.phone.repository.Record
class BillingServiceTest extends FlatSpec with Matchers {

  it should "create zero bill for one record according to promotion" in {
    val records = Seq(PricedRecord("B", "555-333-212", 9.0))
    val bills = createBill(records).toSeq
    bills should have size 1
    bills.head shouldBe Bill("B", 0.0)
  }

  it should "create non-zero bill for two records with different phone  according to promotion" in {
    val records = Seq(PricedRecord("B", "555-333-212", 9.0), PricedRecord("B", "555-333-213", 11.0))
    val bills = createBill(records).toSeq
    bills should have size 1
    bills.head shouldBe Bill("B", 9.0)
  }

  it should "create empty bills for empty records" in {
    val records = Seq()
    val bills = createBill(records).toSeq
    bills should have size 0
  }

  it should "create 2 non-zero bills for two clients" in {
    val records = Seq(
      PricedRecord("B", "555-333-212", 9.0),
      PricedRecord("B", "555-333-213", 11.0),
      PricedRecord("A", "555-333-212", 10.0),
      PricedRecord("A", "555-333-213", 12.0))

    val bills = createBill(records).toSeq
    bills should have size 2
    bills.head shouldBe Bill("A", 10.0)
    bills(1) shouldBe Bill("B", 9.0)
  }
}
