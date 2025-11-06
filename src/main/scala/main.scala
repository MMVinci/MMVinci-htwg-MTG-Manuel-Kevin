package mtg

import mtg.cards.CardLoader

object Main extends App {
    println("Starting MTG Alpha Simulator Load Test...")
    
    val alphaCards = CardLoader.loadCards()

    if (alphaCards.nonEmpty) {
        println(s"\nLoad successful. Loaded ${alphaCards.size} cards.")
        println("First Card Details:")
        println(alphaCards.head)
    } else {
        println("\nLoad failed. No cards were loaded.")
    }
}