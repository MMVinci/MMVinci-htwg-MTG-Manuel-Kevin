import scala.util.Random
import scala.io.StdIn.readLine
import java.util.Scanner

@main def hello(): Unit =
  val scanner = new java.util.Scanner(System.in); // Lebenspunkte der Soldaten
  var array: Array[Int] = Array(5, 4, 7); // Variable für die Angriffsziele
  var ausgewaehlt: Int = 0;

  // Die Schleife läuft, solange Soldat 0 lebt und mindestens einer der Feinde noch lebt
  while (array(0) > 0 && (array(1) > 0 || array(2) > 0)) {
    println("Hallo! Soldat 0, vor dir stehen zwei Feinde.")
    printf("Soldat 1 mit %d Leben\n", array(1))
    printf("Soldat 2 mit %d Leben\n", array(2))

    println("Wen willst du angreifen?")
    ausgewaehlt = scanner.nextInt()

    schaden(ausgewaehlt, wuerfeln(), array)

    if (array(1) > 0 || array(2) > 0) {
      println("Du wirst angegriffen. Willst du dich verteidigen? (Y|N)")
      scanner.nextLine() // Clear the buffer
      if (scanner.nextLine().equalsIgnoreCase("Y")) {
        verteidigen(0, wuerfeln(), array)
      }
    }
  }

  println("Game over")

def wuerfeln(): Int = Random.between(1, 6)

def verteidigen(person: Int, wuerfel: Int, array: Array[Int]): Unit = {
  printf("Eine %d wurde gewürfelt\n", wuerfel)
  if (wuerfel >= 5) {
    println("Erfolg, kein Damage!")
  } else {
    println("Kein Erfolg, Würfle Damage!")
    schaden(person, wuerfel, array)
  }
}

def schaden(person: Int, wuerfel: Int, array: Array[Int]): Unit = {
  println(s"AU! Eine $wuerfel Schaden wurde gewürfelt!")
  array(person) = array(person) - wuerfel
  if (array(person) <= 0) {
    println(s"Soldat $person wurde besiegt!")
  }
}
