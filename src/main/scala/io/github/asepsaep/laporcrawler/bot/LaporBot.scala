package io.github.asepsaep.laporcrawler.bot

import scala.collection.JavaConverters._

import org.jsoup.nodes.Document
import org.jsoup.Jsoup

import io.github.asepsaep.laporcrawler.model.Ticket

case class LaporBot(ticketId: Int) {

  private var ticket = new Ticket()
  private val url = "http://36.66.86.72/pengaduan/" + ticketId

  //  System.setProperty("socksProxyHost", "127.0.0.1")
  //  System.setProperty("socksProxyPort", "10001")
  //  System.setProperty("socksProxyVersion", "5")

  def crawl(): Option[Ticket] = {
    val doc = Jsoup.connect(url).timeout(30000).get()
    val maybeTicket = if (doc.getElementsByClass("no-data").isEmpty) Option(parse(doc)) else None
    maybeTicket
  }

  def parse(doc: Document): Ticket = {
    val id = ticketId
    val title = doc.getElementById("row_Subject").text
    val splitContent = doc.getElementById("row_content").text.split(", ")
    val content = if (splitContent.length > 1) splitContent.tail.mkString(", ") else doc.getElementById("row_content").text

    ticket = ticket.copy(id = id, title = title, content = content)

    val details = doc.getElementsByClass("feedback-details").first.getElementsByTag("p").asScala
    for (p ← details) {
      val span = p.getElementsByTag("span")
      span.first.text match {
        case "USER:"     ⇒ ticket = ticket.copy(user = Some(span.last.text))
        case "PLATFORM:" ⇒ ticket = ticket.copy(platform = Some(span.last.text))
        case "TANGGAL:"  ⇒ ticket = ticket.copy(date = Some(span.last.text))
        case "KATEGORI:" ⇒ ticket = ticket.copy(category = Some(span.last.text))
        case "AREA:"     ⇒ ticket = ticket.copy(area = Some(span.last.text))
        case "STATUS:"   ⇒ ticket = ticket.copy(status = Some(span.last.text))
        case _           ⇒ {}
      }
    }

    val dispatchedTo = doc.select(".administrator .comment-content").first.getElementsByTag("p").first.getElementsByTag("span").first.getElementsByTag("b").first.text
    ticket = ticket.copy(dispatchedTo = Some(dispatchedTo))

    ticket
  }

}
