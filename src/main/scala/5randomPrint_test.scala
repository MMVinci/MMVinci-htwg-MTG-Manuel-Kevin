package mtg

import mtg.cards.{CardLoader, Deck}

object Main extends App {
  println("=== Magic the Gathering Card Loader and Deck Test ===")

  // 1. Karten laden (nutzt die Standarddatei, die in CardLoader.scala definiert ist,
  // oder 'src/test/resources/dragons.json', wenn dies der Standard ist)
  val dragonCards = CardLoader.loadDefaultCards()

  if (dragonCards.nonEmpty) {
    println(s"Erfolgreich ${dragonCards.size} englische Karten geladen!")
    println("-" * 40)

    // 2. Erstellen und Mischen des Decks
    var dragonDeck = Deck(dragonCards)
    dragonDeck.shuffle() // Mischt den Stapel

    // 3. Starthand ziehen (7 Karten)
    val (hand, updatedDeck) = dragonDeck.draw(7) 
    dragonDeck = updatedDeck // WICHTIG: Die aktualisierte Deck-Instanz speichern

    println(s"Karten im Deck verbleibend: ${dragonDeck.size}")
    println("\n--- Starthand (7 Karten) ---")

    // 4. Starthand anzeigen, unter Verwendung der korrigierten Feldnamen
    hand.foreach { card =>
      println(s"Name: ${card.name}")
      // NEU: cardType, manaValue, manaCost
      println(s"Typ: ${card.cardType}") 
      println(s"Mana Value (CMC): ${card.manaValue}") 
      println(s"Mana Cost: ${card.manaCost.getOrElse("None")}") 
      
      // NEU: power/toughness sind Option[String]
      println(s"Power/Toughness: ${card.power.getOrElse("-")}/${card.toughness.getOrElse("-")}")
      println("-" * 20)
    }
  } else {
    println("FEHLER: Es konnten keine spielbaren Karten geladen werden.")
  }
}