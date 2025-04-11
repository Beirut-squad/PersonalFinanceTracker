class Colors {
    fun redColorText(text: String): String {
        val redColor = "\u001B[31m"
        val resetColor = "\u001B[0m"
        return "$redColor$text$resetColor"
    }

    fun greenColorText(text: String): String {
        val greenColor = "\u001B[32m"
        val resetColor = "\u001B[0m"
        return "$greenColor$text$resetColor"
    }

    fun blueStars(): String {
        val blue = "\u001B[34m"
        val reset = "\u001B[0m"
        return "$blue****************************************************************$reset"
    }
    fun purpleColorText(text: String): String {
        val purple = "\u001B[35m"
        val reset = "\u001B[0m"
        return "$purple$text$reset"
    }


}
