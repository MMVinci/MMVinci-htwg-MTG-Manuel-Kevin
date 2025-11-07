package mtg.cards

import io.circe.Json // Wird benötigt, wenn Sie die "meta"-Daten im JSON ignorieren oder speichern wollen

/**
 * Das erweiterte Card Modell, optimiert für die MTG-Gameplay-Simulation.
 * Die Feldnamen entsprechen den Attributen in MTGJSON.
 */
case class Card(
  // Identifikation
  name: String,
  language: String,
  // Kosten & Werte
  manaCost: Option[String],   // Z.B. "{1}{R}"
  manaValue: Double,          // Das Converted Mana Cost / Mana Value
  
  // Typen & Farben
  cardType: String,           // Der vollständige Typ-String (z.B. "Legendary Creature — Human Warrior")
  supertypes: List[String],   // Z.B. List("Legendary", "Basic")
  subtypes: List[String],     // Z.B. List("Human", "Warrior")
  colors: List[String],       // Die Farbe(n) der Karte
  
  // Regeln & Fähigkeiten
  text: Option[String],       // Der wichtigste Teil: Der Regelschriftzug
  keywords: Option[List[String]], // Z.B. List("Flying", "Trample")
  
  // Stats (Option[String] für * und X)
  power: Option[String],
  toughness: Option[String],
  loyalty: Option[String],    // Für Planeswalker
  
  // Besondere Karten-Layouts
  layout: String,             // Z.B. "normal", "transform", "split"
  otherFaceIds: Option[List[String]] // Für die andere Seite einer verwandelbaren Karte
)

/**
 * HILFSKLASSE: Spiegelt die äußere Struktur einer typischen MTGJSON-Datei wider.
 * Die Daten sind als Map[Kartenname, Card-Objekt] im Feld "data" enthalten.
 */
case class MtgJsonData(
    data: Map[String, Card], 
    // Optionale: Das Feld "meta" kann als generisches Json-Objekt gespeichert oder ignoriert werden.
    meta: Option[Json] = None 
)