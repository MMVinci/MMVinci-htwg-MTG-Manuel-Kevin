package mtg.cards

import scala.io.StdIn.readLine

object CardManager {

    // HINWEIS: CardLoader.loadCards() müsste in CardLoader.loadDefaultCards() umbenannt werden,
    // falls Sie die neue Methode verwenden wollen. Hier belassen wir es bei der alten Signatur.
    def main(args: Array[String]): Unit = {
        println("=== Magic the Gathering Card Manager ===")

        // Karten laden
        // HINWEIS: Es ist besser, CardLoader.loadDefaultCards() zu verwenden, wenn Sie die neue Logik in CardLoader haben.
        val cards = CardLoader.loadCards() 
        
        var running = true
        while (running) {
            print("> ")
            val input = readLine().trim 

            input match {
                case "help!" =>
                    println("Available commands:")
                    println("  list: Show all loaded cards.")
                    println("  search <name>: Find cards whose name contains the query.")
                    println("  filter <type>: Find cards whose card type contains the query (e.g., 'creature').")
                    println("  exit: Quit the program.")

                case "list" =>
                    if (cards.isEmpty) println("No cards available.")
                    else cards.foreach(showCard)

                case cmd if cmd.startsWith("search ") =>
                    val name = cmd.stripPrefix("search ").trim.toLowerCase
                    val result = cards.filter(_.name.toLowerCase.contains(name))
                    
                    if (result.isEmpty) println(s"No cards found with name containing '$name'.")
                    else result.foreach(showCard)

                case cmd if cmd.startsWith("filter ") =>
                    val typ = cmd.stripPrefix("filter ").trim.toLowerCase
                    
                    // ✨ KORREKTUR: Verwende 'cardType' anstelle von 'type'
                    val result = cards.filter(_.cardType.toLowerCase.contains(typ))
                    
                    if (result.isEmpty) println(s"No cards found of type '$typ'.")
                    else result.foreach(showCard)

                case "exit" | "quit" =>
                    running = false
                    println("Program beendet.")

                case "" => // ignore
                case other =>
                    println(s"Unknown command: '$other'.") 
                    println("Available commands: help!, list, search, filter, exit.")
            }
        }
    }
    
    private def showCard(card: Card): Unit = {
        val pt = (card.power, card.toughness) match {
            case (Some(p), Some(t)) => s"P/T: $p/$t"
            case _ => "P/T: -"
        }
        val cost = card.manaCost.getOrElse("N/A")
        val keywords = if (card.keywords.nonEmpty) card.keywords.mkString(", ") else "None"
        
        println("----------------------------------------")
        println(s"Name: ${card.name}")
        println(s"Typ: ${card.cardType}") 
        println(s"Kosten: $cost (CMC: ${card.manaValue})")
        println(s"Keywords: $keywords")
        println(pt)
    }
}