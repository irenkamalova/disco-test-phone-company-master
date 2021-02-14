package com.phone.billing

object TariffService {

  /**
   * For a customer the cost of a call up to and including 3 minutes in duration is charged at 0.05p/sec,
   * any call over 3 minutes in duration the additional time is charged at 0.03p/sec.
   */
  def computePrice(duration: Long): Double = {
    if (duration <= 180L) {
      duration * 0.05
    } else {
      val lowPriced = duration - 180L
      lowPriced * 0.03 + 9
    }
  }
}
