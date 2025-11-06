package mtg.cards

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import java.io.File

class CardLoaderSpec extends AnyFunSpec with Matchers {

  private val alphaPath    = "src/test/resources/alpha_cards.json"
  private val shivanPath   = "src/test/resources/shivan.json"
  private val invalidPath  = "src/test/resources/invalid.json"
  private val emptyPath    = "src/test/resources/empty.json"

  describe("CardLoader") {

    it("should successfully load the list of cards from a valid JSON file") {
      val loadedCards = CardLoader.loadCards(alphaPath)
      loadedCards should not be empty
      loadedCards.size shouldBe 3
    }

    it("should correctly parse Black Lotus details") {
      val blackLotus = CardLoader.loadCards(alphaPath).head
      blackLotus.name shouldBe "Black Lotus"
      blackLotus.`type` shouldBe "Artifact"
      blackLotus.mana_cost shouldBe "0"
      blackLotus.rarity shouldBe "Rare"
      blackLotus.set shouldBe "Alpha"
      blackLotus.power shouldBe None
      blackLotus.toughness shouldBe None
    }

    it("should correctly parse Shivan Dragon details") {
      val dragon = CardLoader.loadCards(shivanPath).head
      dragon.name shouldBe "Shivan Dragon"
      dragon.`type` shouldBe "Creature"
      dragon.mana_cost shouldBe "4RR"
      dragon.rarity shouldBe "Rare"
      dragon.set shouldBe "Alpha"
      dragon.power shouldBe Some(5)
      dragon.toughness shouldBe Some(5)
    }

    it("should return an empty list when the file does not exist") {
      val loadedCards = CardLoader.loadCards("nonexistent.json")
      loadedCards shouldBe empty
    }

    it("should return an empty list on invalid JSON") {
      val loadedCards = CardLoader.loadCards(invalidPath)
      loadedCards shouldBe empty
    }

    it("should return an empty list on empty JSON array") {
      val loadedCards = CardLoader.loadCards(emptyPath)
      loadedCards shouldBe empty
    }
  }
}
