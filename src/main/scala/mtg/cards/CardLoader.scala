package mtg.cards

import scala.io.Source
import io.circe._, io.circe.generic.auto._, io.circe.parser._
import scala.util.{Try, Using} 

object CardLoader {
    private val defaultFilePath = "src/main/resources/alpha_cards.json"

    def loadCards(filePath: String = defaultFilePath): List[Card] = {

        val jsonLoadAttempt: Try[String] = Using(Source.fromFile(filePath)) { source =>
            source.getLines().mkString
        } // KEIN .toTry mehr hier

        jsonLoadAttempt match {
            case scala.util.Success(jsonString) => // jsonString ist nun korrekt als String erkannt
                decode[List[Card]](jsonString) match {
                    case Right(cards) => 
                        println(s"Successfully loaded ${cards.size} cards from $filePath.")
                        cards
                    case Left(error) =>
                        println(s"Failed to decode JSON from $filePath: $error")
                        List.empty
                }
            case scala.util.Failure(exception) =>
                println(s"Failed to read file at $filePath: ${exception.getMessage}")
                List.empty
        }
    }
}