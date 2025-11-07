package mtg.cards

import scala.io.StdIn.readLine

// Beispiel (für die Kompilierung notwendig):
// case class Card(name: String, mana_cost: String, `type`: String, rarity: String, set: String)
// object CardLoader { def loadCards(): Seq[Card] = Seq.empty[Card] }

object CardManager {

    def main(args: Array[String]): Unit = {
        println("=== Magic the Gathering Card Manager ===")

        // Karten laden
        val cards = CardLoader.loadCards()
        
        var running = true
        while (running) {
            print("> ")
            val input = readLine().trim 

            input match {
                case "list" =>
                    if (cards.isEmpty) println("No cards available.")
                    else cards.foreach(showCard)

                // 1. und 2. Fehlerkorrektur: Pattern Matching für 'search'
                case cmd if cmd.startsWith("search ") =>
                    // Name extrahieren und trimmen
                    val name = cmd.stripPrefix("search ").trim.toLowerCase
                    
                    val result = cards.filter(_.name.toLowerCase.contains(name))
                    
                    if (result.isEmpty) println(s"No cards found with name containing '$name'.")
                    else result.foreach(showCard)

                // 3. Fehlerkorrektur: Pattern Matching und Fehler im Methodenaufruf
                case cmd if cmd.startsWith("filter ") =>
                    // Typ extrahieren und trimmen
                    val typ = cmd.stripPrefix("filter ").trim.toLowerCase
                    
                    val result = cards.filter(_.`type`.toLowerCase.contains(typ))
                    
                    if (result.isEmpty) println(s"No cards found of type '$typ'.")
                    else result.foreach(showCard)

                case "exit" =>
                    running = false
                    println("Program beendet.")

                case "" => // ignore
                case other =>
                    println(s"Unknown command: '$other'.") 
                    println("Available commands: list, search <name>, filter <type>, exit.")
            }
        }
    }
    
    private def showCard(card: Card): Unit = {
        // Der Name 'type' in Scala ist ein reserviertes Wort, 
        // daher muss es mit Backticks (`) umschlossen werden.
        println(s"- ${card.name} (${card.`type`}), Mana Cost: ${card.mana_cost}, Rarity: ${card.rarity}, Set: ${card.set}")
    }
}