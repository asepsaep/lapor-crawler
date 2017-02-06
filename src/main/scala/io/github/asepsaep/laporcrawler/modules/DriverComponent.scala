package io.github.asepsaep.laporcrawler.modules

import slick.driver.JdbcDriver

trait DriverComponent {
  val driver: JdbcDriver
}