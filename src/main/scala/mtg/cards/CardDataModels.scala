package mtg.cards

case class Card(
  name: String,
  `type`: String,
  mana_cost: String,
  rarity: String,
  set: String,
  text: String,
  power: Option[Int],
  toughness: Option[Int]
)
