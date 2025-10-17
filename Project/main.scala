object Spielfeld {
    
    def main(args: Array[String]): Unit = {
        
        println("CardList of 31er: ")


        val backCard  = "┌─────┐\n" +
                        "│#####│\n" *3 +
                        "└─────┘\n"

        println(backCard)

        val v = Array("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
        //val s = Array("♠", "♥", "♦", "♣")
        //spades, hearts, diamonds, clubs
        val s = Array("S", "H", "D", "C")
        


        for {
            wert <- v
            symbol <- s
        } {
            val wertL = wert.padTo(2, ' ')
            val wertR = wert.padTo(2, ' ').reverse
            //val symbolL = symbol.padTo(2, ' ')

            val valCard = s"┌─────┐\n" +
                          s"│$wertL   │\n" +
                          s"│  $symbol  │\n" +
                          s"│   $wertR│\n" +
                          s"└─────┘\n"


            println(valCard + "\n")	
        }


    }
}