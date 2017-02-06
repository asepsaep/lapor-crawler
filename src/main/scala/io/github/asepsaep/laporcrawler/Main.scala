package io.github.asepsaep.laporcrawler

import scala.concurrent.{ Await, Future }
import scala.concurrent.duration._
import bot._
import model._
import modules._
import repository._
import scala.concurrent.ExecutionContext.Implicits.global

import scala.util.{ Failure, Success }

object Main {

  def main(args: Array[String]): Unit = {

    object Tickets extends TicketComponent with PostgresPersistance

    import Tickets._
    import Tickets.driver.api._

    import scala.io.Source
    val numbers = Source.fromFile("/home/asep/Documents/dataset/aduan/id_data_latih.txt").getLines.toArray.map(t ⇒ t.toInt)

    val dl = Future {
      for (i ← numbers) {
        TicketRepository.exists(i).map { result ⇒
          if (result.isEmpty) {
            println(s"Downloading #$i...")
            LaporBot(i).crawl().fold(println(s"Ticket $i: Not Found")) { ticket ⇒
              TicketRepository.insert(ticket).onComplete {
                case Success(value) ⇒ s"Ticket $i: Success"
                case Failure(e)     ⇒ e.getMessage
              }
            }
          }
        }
      }
    }

    Await.result(dl, Duration.Inf)
//
//    for (i ← 1570187 to 1500000 by -1) {
//      val result = LaporBot(i).crawl().fold(s"Ticket $i: Not Found") { ticket ⇒
//        Await.result(TicketRepository.insert(ticket), 2.seconds)
//        s"Ticket $i: Success"
//      }
//      println(result)
//    }

    //    for (i ← numbers) {
    //      val result = LaporBot(i).crawl().fold(s"Ticket $i: Not Found") { ticket ⇒
    //        Await.result(TicketRepository.insert(ticket), Duration.Inf)
    //        s"Ticket $i: Success"
    //      }
    //      println(result)
    //    }

    println("<=DONE=>")

  }

}
