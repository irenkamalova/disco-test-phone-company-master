package com.phone.config

import com.typesafe.config.ConfigFactory

object ApplicationConfig {
  val config = ConfigFactory.defaultApplication()
  val fileName = config.getString("filename")
}
