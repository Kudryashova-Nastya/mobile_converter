package main

class HenFactory() {
    fun getHen(country: String): Hen? {
        val map = mapOf("Россия" to RussianHen(), "Украина" to UkrainianHen(),
            "Молдова" to MoldovanHen(), "Беларусь" to BelarusianHen())
        return map[country]
    }
}