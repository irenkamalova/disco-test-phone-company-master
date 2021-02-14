package com.phone.parser

import java.io.FileNotFoundException
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoField

import com.phone.config.SimpleLogger.{debug, error}
import com.phone.repository.Record

import scala.io.Source
import scala.util.{Failure, Success, Try}

object Parser {

  def parse(fileName: String)(f: Record => Either[Exception, Boolean]): Either[Exception, Boolean] = {
    Try(Source.fromResource(fileName))
      .map { s =>
        for (line <- s.getLines) {
          debug(line)
          Try(parseLine(line)) match {
            case Success(record) => f(record)
            case Failure(e) => error(e.getMessage)
          }
        }
      }
    match {
      case Success(_) => Right(true)
      case Failure(e) =>
        error(e.getMessage)
        Left(new FileNotFoundException(fileName))
    }
  }

  def parseLine(line: String): Record = {
    val tokens = line.split("\\s+")
    val client = tokens(0)
    val phoneNumber = tokens(1)
    val callDuration = tokens(2)
    val datetimeFormat = DateTimeFormatter.ofPattern("HH:mm:ss")

    val duration = datetimeFormat.parse(callDuration)
    Record(client, phoneNumber, duration.getLong(ChronoField.SECOND_OF_DAY))
  }
}
