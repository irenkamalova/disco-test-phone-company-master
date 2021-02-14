package com.phone

import com.phone.parser.Parser._
import com.phone.repository.Record
import org.scalatest.{FlatSpec, Matchers}

class ParserTest extends FlatSpec with Matchers {

  val f: Record => Either[Exception, Boolean] = record => Right(true)

  it should "correctly parse line" in {
    parseLine("B 555-333-212 00:04:31") shouldBe Record("B", "555-333-212", 271L)
  }

  it should "parse existing file" in {
    parse("test_calls.log")(f) shouldBe 'right
  }

  it should "parse empty existing file" in {
    parse("test_calls.log")(f) shouldBe 'right
  }

  it should "fail to parse when file doesn't exist" in {
    parse("test.log")(f)  shouldBe 'left
  }
}
