package mtg.cards

import scala.io.{Source, Codec}
import io.circe._, io.circe.generic.auto._, io.circe.parser._
import scala.util.{Try, Using}
import java.nio.charset.StandardCharsets

object CardLoader {
    // Standardpfad, der in den Tests verwendet wird
    private val defaultFilePath = "src/test/resources/dragons_test.json"

    def loadCards(filePath: String = defaultFilePath): List[Card] = {
        println(s"Attempting to load data from $filePath...")
        
        // HIER WIRD DIE KORREKTUR VORGENOMMEN: 
        // Wir verwenden Using und Source.fromFile mit explizitem Codec.UTF8
        val jsonLoadAttempt = Using(Source.fromFile(filePath)(Codec.UTF8)) { source =>
            source.getLines().mkString
        }

        jsonLoadAttempt match {
            case scala.util.Success(jsonString) =>
                decode[MtgJsonData](jsonString) match {
                    case Right(mtgData) =>
                        val allCards = mtgData.data.values.toList
                        println(s"Successfully loaded ${allCards.size} total card entries from $filePath.")
                        
                        // WICHTIGER SCHRITT: Filtern der Karten nach der Sprache "English"
                        val englishCards = allCards
                            .filter(_.cardType.nonEmpty) 
                            .filter(_.language == "English") 
                        
                        println(s"Found ${englishCards.size} playable English cards.")
                        englishCards
                        
                    case Left(error) =>
                        println(s"Failed to decode JSON structure from $filePath.")
                        println(s"Decoding Error: $error")
                        List.empty
                }
            case scala.util.Failure(exception) =>
                println(s"Failed to read file at $filePath: ${exception.getMessage}")
                List.empty
        }
    }

    def loadDefaultCards(): List[Card] = {
        val cards = loadCards(defaultFilePath)
        println(s"${cards.size} englische Spielkarten erfolgreich geladen!\n")
        cards
    }
}