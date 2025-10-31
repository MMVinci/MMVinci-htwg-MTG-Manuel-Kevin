import scala.io.StdIn.readLine
import scala.util.Random
import java.util.Scanner

def wurfeln(): Int = Random.between(1, 6)

def verteidigen(person: Int, wuerfel: Int, array: Array[Int]): Unit =
  println("Würfel wird geworfen für Verteidigung...")
  Thread.sleep(1000) // Simuliere Wurfzeit
  println(s"Eine $wuerfel wurde gewürfelt!")
  if wuerfel >= 5 then
    println("Erfolg – kein Schaden!")
  else
    println("Kein Erfolg, du erleidest Schaden!")
    schaden(person, wuerfel, array)

def schaden(person: Int, wuerfel: Int, array: Array[Int]): Unit =
  println(s"AU! Ein Schaden von $wuerfel wurde gewürfelt!")
  if person >= 0 && person < array.length then
    array(person) = (array(person) - wuerfel).max(0)
    if array(person) == 0 then
      println(s"💀 Soldat $person wurde besiegt!")
    else
      println(s"➡️ Soldat $person hat jetzt ${array(person)} Leben übrig.")
  else
    println(s"❌ Ungültiger Index: $person")

import scala.io.StdIn.readLine

def leseZiel(): Int =
  var ziel: Option[Int] = None
  while ziel.isEmpty do
    val input = readLine("Wen willst du angreifen? (1 oder 2): ")
    input.trim match
      case "1" | "2" => ziel = Some(input.toInt)
      case _ =>
        println("❌ Ungültige Eingabe! Bitte 1 oder 2 eingeben.")
        println("👉 Beispiel: 1 für Soldat 1, 2 für Soldat 2")
  ziel.get


@main def hello(): Unit =
  val scanner = new java.util.Scanner(System.in)
  var leben = Array(5, 4, 7)
  var ausgewaehlt = 0

  def leseZahl(gueltige: Set[Int]): Int =
    var wert = -1
    var korrekt = false
    while !korrekt do
      print(s"Gib eine Zahl ein (${gueltige.mkString(" oder ")}): ")
      if scanner.hasNextInt() then
        val eingabe = scanner.nextInt()
        if gueltige.contains(eingabe) then
          wert = eingabe
          korrekt = true
        else
          println(s"❌ Ungültige Zahl! Bitte ${gueltige.mkString(" oder ")} eingeben.")
      else
        println("❌ Falsche Eingabe! Bitte eine Zahl eingeben.")
        scanner.next() // ungültige Eingabe aus dem Buffer löschen
    wert

  while leben(0) > 0 && (leben(1) > 0 || leben(2) > 0) do
    println("\nHallo! Soldat 0, vor dir stehen zwei Feinde.")
    println(s"Soldat 1 mit ${leben(1)} Leben")
    println(s"Soldat 2 mit ${leben(2)} Leben")

    println("Wen willst du angreifen?")
    ausgewaehlt = leseZahl(Set(1, 2))

    schaden(ausgewaehlt, wuerfeln(), leben)

    if leben(1) > 0 || leben(2) > 0 then
      println("Du wirst angegriffen. Willst du dich verteidigen? (Y|N)")
      scanner.nextLine() // Buffer leeren nach nextInt()
      var eingabe = scanner.nextLine().trim.toUpperCase
      while eingabe != "Y" && eingabe != "N" do
        println("❌ Ungültige Eingabe! Bitte Y oder N eingeben.")
        eingabe = scanner.nextLine().trim.toUpperCase
      if eingabe == "Y" then
        verteidigen(0, wuerfeln(), leben)

  println("\nSpiel beendet!")
  if leben(0) <= 0 then println("💀 Du wurdest besiegt!")
  else println("🎉 Du hast gewonnen!")
