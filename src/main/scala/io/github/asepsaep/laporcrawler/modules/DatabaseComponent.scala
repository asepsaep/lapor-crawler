package io.github.asepsaep.laporcrawler.modules

import slick.jdbc.JdbcBackend.Database

trait DatabaseComponent {
  val db: Database
}