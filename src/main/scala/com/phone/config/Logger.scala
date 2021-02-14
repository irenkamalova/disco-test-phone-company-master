package com.phone.config

trait Logger {
  def debug(message: String)
  def error(message: String)
}

object SimpleLogger extends Logger {
  override def debug(message: String): Unit = {
    println(message)
  }

  override def error(message: String): Unit = {
    println(message)
  }
}
