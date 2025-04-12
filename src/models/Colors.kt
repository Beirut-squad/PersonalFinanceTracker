class Colors {
    fun printRedColorText(text: String) {
        val redColor = "\u001B[31m"
        val resetColor = "\u001B[0m"
         println("$redColor$text$resetColor")
    }
    fun printGreenColorText(text: String) {
        val greenColor = "\u001B[32m"
        val resetColor = "\u001B[0m"
        println("$greenColor$text$resetColor")
    }

    fun blueStars(): String {
        val blue = "\u001B[34m"
        val reset = "\u001B[0m"
        return "$blue****************************************************************$reset"
    }

    fun printPurpleColorText(text: String)  {
        val purple = "\u001B[35m"
        val reset = "\u001B[0m"
        println("$purple$text$reset")
    }


}
