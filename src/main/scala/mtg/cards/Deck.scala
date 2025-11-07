// Deck.scala
package mtg.cards

import scala.util.Random

case class Deck(initialCards: List[Card]) {
  
  private var deckStack: List[Card] = initialCards

  def shuffle(): Unit = {
    deckStack = Random.shuffle(deckStack)
    println(s"Deck mit ${deckStack.size} Karten gemischt.")
  }

  def draw(count: Int): (List[Card], Deck) = {
    if (count <= 0) {
      (List.empty, this)
    } else {
      val (drawnCards, remainingStack) = deckStack.splitAt(count)
      deckStack = remainingStack 
      (drawnCards, this)
    }
  }

  def size: Int = deckStack.size
  def isEmpty: Boolean = deckStack.isEmpty
}