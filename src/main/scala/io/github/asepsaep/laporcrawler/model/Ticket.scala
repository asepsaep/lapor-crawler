package io.github.asepsaep.laporcrawler.model

case class Ticket(
  id: Int = 0,
  title: String = "",
  content: String = "",
  user: Option[String] = None,
  date: Option[String] = None,
  platform: Option[String] = None,
  category: Option[String] = None,
  area: Option[String] = None,
  status: Option[String] = None,
  dispatchedTo: Option[String] = None
)