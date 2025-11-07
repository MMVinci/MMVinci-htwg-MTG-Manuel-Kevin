import mtg.cards.CardLoader

object Main extends App {
  println("=== Magic the Gathering Card Loader ===")

  // Karten laden (Standarddatei)
  val cards = CardLoader.loadCards()

  println(s"Successfully loaded ${cards.size} cards!\n")

  // Karten anzeigen
  cards.foreach { card =>
    println(s"Name: ${card.name}")
    println(s"Type: ${card.`type`}")
    println(s"Mana Cost: ${card.mana_cost}")
    println(s"Rarity: ${card.rarity}")
    println(s"Set: ${card.set}")
    println(s"Power: ${card.power.getOrElse("-")}")
    println(s"Toughness: ${card.toughness.getOrElse("-")}")
    println("-" * 40)
  }
}
