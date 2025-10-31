import scala.util.Random
import java.util.Scanner

val scanner = new java.util.Scanner(System.in)
var array: Array[Int] = Array(5, 4, 7) // Lebenspunkte der Soldaten
var ausgewaehlt: Int = 0 // Variable für die Angriffsziele

// Die Schleife läuft, solange Soldat 0 lebt und mindestens einer der Feinde noch lebt
while (array(0) >= 0 && (array(1) > 0 || array(2) > 0)) {
  println("Du bist Soldat 0")
  println("Es gibt folgende Feinde:")
  printf("Soldat 1 mit %d Leben\n", array(1))
  printf("Soldat 2 mit %d Leben\n", array(2))

  // Eingabevalidierung für den Angriffsziel
  println("Wen willst du angreifen?")

  if (scanner.hasNextInt()) { // Prüfe, ob die Eingabe eine Zahl ist
    ausgewaehlt = scanner.nextInt()
    scanner.nextLine() // Leere den Puffer nach nextInt(), um den Zeilenumbruch zu entfernen
  } else {
    println("Ungültige Eingabe! Bitte eine Zahl eingeben.")
    scanner.nextLine() // Leere den Puffer, wenn die Eingabe nicht korrekt ist
    ausgewaehlt = -1 // Setze einen ungültigen Wert, falls die Eingabe falsch ist
  }

  // Prüfe, ob das gewählte Ziel gültig ist (zwischen 0 und 2)
  if (ausgewaehlt >= 0 && ausgewaehlt < array.length) {
    schaden(ausgewaehlt, wuerfeln(), array) // Wende Schaden auf das gewählte Ziel an
  } else {
    println("Ungültige Auswahl! Versuche es erneut.")
  }

  // Wenn Soldat 1 oder Soldat 2 noch lebt, greifen sie Soldat 0 an
  if (array(1) > 0 || array(2) > 0) {
    println("Du wirst angegriffen. Willst du dich verteidigen? (Y|N)")
    val choice = scanner.nextLine().toUpperCase() // Erhalte die Verteidigungsantwort
    if (choice == "Y") {
      verteidigen(0, wuerfeln(), array) // Verteidige Soldat 0
    }
  }
}

println("Game over")

// Funktion zum Würfeln eines Schadens
def wuerfeln(): Int = Random.between(1, 6)

// Verteidigungsfunktion
def verteidigen(person: Int, wuerfel: Int, array: Array[Int]): Unit = {
  printf("Eine %d wurde gewürfelt\n", wuerfel)
  if (wuerfel >= 5) {
    println("Erfolg, kein Damage!") // Verteidigung erfolgreich, kein Schaden
  } else {
    println("Kein Erfolg, Würfle Damage!") // Verteidigung fehlgeschlagen, Schaden wird erlitten
    schaden(person, wuerfel, array)
  }
}

// Schadensfunktion
def schaden(person: Int, wuerfel: Int, array: Array[Int]): Unit = {
  println(s"AU! Eine $wuerfel Schaden wurde gewürfelt!")
  array(person) = array(person) - wuerfel // Ziehe Schaden von den Lebenspunkten ab
  if (array(person) <= 0) {
    println(s"Soldat $person wurde besiegt!") // Soldat ist besiegt, wenn Lebenspunkte 0 oder weniger sind
  }
}