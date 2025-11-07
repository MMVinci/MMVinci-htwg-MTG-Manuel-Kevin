package mtg.cards

import org.scalatest.funspec.AnyFunSpec
import org.scalatest.matchers.should.Matchers
import java.io.File

class CardLoaderSpec extends AnyFunSpec with Matchers {

  // ✨ FINAL KORRIGIERTE DEFINITIONEN, um IHRER Dateistruktur zu entsprechen:
  // 1. dragons_test.json
  // 2. mixed_lenguages_test.json (übernimmt Ihren Tippfehler für den Pfad)
  private val dragonsDeckPath   = "src/test/resources/dragons_test.json" 
  private val mixedLangPath     = "src/test/resources/mixed_lenguages_test.json"
  private val invalidStructPath = "src/test/resources/invalid_mtg_structure.json" 

  describe("CardLoader") {

    // --- 1. Grundlegender Ladetest und Sprachfilter ---
    it("should successfully load only English cards from dragons.json") {
      val loadedCards = CardLoader.loadCards(dragonsDeckPath)
      loadedCards should not be empty
      loadedCards.size shouldBe 32 
      loadedCards.foreach(_.language shouldBe "English")
    }
    
    // --- 2. Spezifischer Kartentest (Bogardan Hellkite) ---
    it("should correctly parse Bogardan Hellkite details with the new Card model") {
      val hellkite = CardLoader.loadCards(dragonsDeckPath).find(_.name == "Bogardan Hellkite")
      hellkite shouldBe defined
      
      val card = hellkite.get
      
      card.name shouldBe "Bogardan Hellkite"
      card.cardType shouldBe "Creature — Dragon" 
      card.manaValue shouldBe 8.0 
      card.power shouldBe Some("5")
      card.toughness shouldBe Some("5")
      card.keywords should contain (List("Flash", "Flying")) 
      card.loyalty shouldBe None
      card.subtypes should contain ("Dragon")
      card.colors should contain ("R")
    }
    
    // --- 3. Test des Sprachfilters (Critical Test) ---
    it("should filter out non-English cards and only load the single English one") {
      val loadedCards = CardLoader.loadCards(mixedLangPath)
      loadedCards.size shouldBe 1
      loadedCards.head.name shouldBe "Test Card English"
      loadedCards.head.language shouldBe "English"
    }

    // --- 4. Fehlerbehandlung (Nicht-existierende Datei) ---
    it("should return an empty list when the file does not exist") {
      val loadedCards = CardLoader.loadCards("nonexistent_file_xyz.json")
      loadedCards shouldBe empty
    }

    // --- 5. Fehlerbehandlung (Ungültige JSON-Struktur) ---
    it("should return an empty list on invalid JSON structure (MtgJsonData decoding error)") {
      val loadedCards = CardLoader.loadCards(invalidStructPath)
      loadedCards shouldBe empty
    }
  }
}