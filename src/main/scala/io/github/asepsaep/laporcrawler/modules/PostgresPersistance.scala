package io.github.asepsaep.laporcrawler.modules

import slick.driver.PostgresDriver
import slick.jdbc.JdbcBackend.Database

trait PostgresPersistance extends DriverComponent with DatabaseComponent {

  val driver = PostgresDriver
  val db = Database.forConfig("db")

}
