package com.phone

import org.scalatest.{FlatSpec, Matchers}
import com.phone.billing.TariffService._

class TariffServiceTest extends FlatSpec with Matchers {

  it should "correctly compute price" in {
    computePrice(0L) shouldBe 0.00
    computePrice(60L) shouldBe 3.00
    computePrice(180L) shouldBe 9.00
    computePrice(181L) shouldBe 9.03
  }
}
