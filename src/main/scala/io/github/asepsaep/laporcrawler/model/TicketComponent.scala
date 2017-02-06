package io.github.asepsaep.laporcrawler.model

import io.github.asepsaep.laporcrawler.modules.{ DatabaseComponent, DriverComponent }
import io.github.asepsaep.laporcrawler.repository.Repository

trait TicketComponent { this: DatabaseComponent with DriverComponent ⇒

  import slick.lifted.Tag
  import driver.api._

  class TicketTable(tag: Tag) extends Table[Ticket](tag, "ticket") {
    def id = column[Int]("id")
    def title = column[String]("title")
    def content = column[String]("content")
    def user = column[String]("user")
    def date = column[String]("date")
    def platform = column[String]("platform")
    def category = column[String]("category")
    def area = column[String]("area")
    def status = column[String]("status")
    def dispatchedTo = column[String]("dispatched_to")

    override def * = (
      id,
      title,
      content,
      user.?,
      date.?,
      platform.?,
      category.?,
      area.?,
      status.?,
      dispatchedTo.?
    ) <> ((Ticket.apply _).tupled, Ticket.unapply _)
  }

  object TicketRepository extends Repository[TicketTable, Int](driver, db) {
    import this.driver.api._

    val table = TableQuery[TicketTable]
    def getId(table: TicketTable) = table.id
  }

}