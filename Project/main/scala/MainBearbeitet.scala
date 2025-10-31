import scala.util.Random
import java.util.Scanner

// Würfeln-Funktion (1 bis 6)
def wuerfeln(): Int =
  Random.between(1, 7) // 1 bis 6

// Schaden-Funktion
def schaden(person: Int, schaden: Int, array: Array[Int]): Unit =
  println(s"AU! Ein Schaden von $schaden wurde verursacht!")
  if person >= 0 && person < array.length then
    array(person) = (array(person) - schaden).max(0)
    if array(person) == 0 then println(s"💀 Soldat $person wurde besiegt!")
    else println(s"➡️ Soldat $person hat jetzt ${array(person)} Leben übrig.")
  else println(s"❌ Ungültiger Index: $person")

// Verteidigen-Funktion
def verteidigen(person: Int, array: Array[Int]): Unit =
  println("Rüstungswurf für Verteidigung wird durchgeführt...")
  Thread.sleep(2000)
  val wurf = wuerfeln()
  println(s"Gewürfelt: $wurf")
  if wurf >= 5 then println("Erfolg – kein Schaden!")
  else
    println("Kein Erfolg, du erleidest Schaden!")
    schaden(person, wuerfeln(), array)

// Hilfsfunktion für sichere Zahleneingabe
def leseZahl(scanner: Scanner, gueltige: Set[Int]): Int =
  var wert = -1
  var korrekt = false
  while !korrekt do
    print(s"Gib eine Zahl ein (${gueltige.mkString(" oder ")}): ")
    if scanner.hasNextInt() then
      val eingabe = scanner.nextInt()
      if gueltige.contains(eingabe) then
        wert = eingabe
        korrekt = true
      else println(s"❌ Ungültige Zahl! Bitte ${gueltige.mkString(" oder ")} eingeben.")
    else
      println("❌ Falsche Eingabe! Bitte eine Zahl eingeben.")
      scanner.next() // ungültige Eingabe aus Buffer löschen
  wert

// Hauptspiel
@main def hello(): Unit =
  val scanner = new Scanner(System.in)
  var leben = Array(5, 4, 7)
  var ausgewaehlt = 0

  while leben(0) > 0 && (leben(1) > 0 || leben(2) > 0) do
    println("\nHallo! Soldat 0, vor dir stehen zwei Feinde.")
    println(s"Soldat 1 mit ${leben(1)} Leben")
    println(s"Soldat 2 mit ${leben(2)} Leben")

    println("Wen willst du angreifen?")
    ausgewaehlt = leseZahl(scanner, Set(1, 2))

    // --- Angriff nach 40K Regeln ---
    println("\nTrefferwurf...")
    Thread.sleep(2000)
    val treffen = wuerfeln()
    println(s"Gewürfelt: $treffen")
    if treffen >= 3 then // 3+ trifft z.B.
      println("Treffer erfolgreich!")
      println("Wundwurf...")
      Thread.sleep(2000)
      val wunde = wuerfeln()
      println(s"Gewürfelt: $wunde")
      if wunde >= 4 then // 4+ verwundet
        println("Wunde erfolgreich!")
        // Rüstungswurf Gegner
        println("Rüstungswurf des Gegners...")
        Thread.sleep(2000)
        val save = wuerfeln()
        println(s"Gewürfelt: $save")
        if save >= 4 then println("Gegner hat den Treffer abgewehrt!")
        else schaden(ausgewaehlt, 1, leben)
      else println("Kein Wundwurf – kein Schaden!")
    else println("Treffer verfehlt!")

    // Verteidigung Spieler
    if leben(1) > 0 || leben(2) > 0 then
      println("\nDu wirst angegriffen. Willst du dich verteidigen? (Y|N)")
      scanner.nextLine() // Buffer leeren
      var eingabe = scanner.nextLine().trim.toUpperCase
      while eingabe != "Y" && eingabe != "N" do
        println("❌ Ungültige Eingabe! Bitte Y oder N eingeben.")
        eingabe = scanner.nextLine().trim.toUpperCase
      if eingabe == "Y" then verteidigen(0, leben)
      else
        println("Du hast nicht verteidigt – du erleidest Schaden!")
        schaden(0, wuerfeln(), leben)

  println("\nSpiel beendet!")
  if leben(0) <= 0 then println("💀 Du wurdest besiegt!")
  else println("🎉 Du hast gewonnen!")
