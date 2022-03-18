package main

class BelarusianHen(): Hen(){
    val name_country = "Беларусь"
    override fun getCountOfEggsPerMonth(): Int {return 18}
    override fun getDescription(): String{
        return "Моя страна - ${name_country}. Я несу ${getCountOfEggsPerMonth()} яиц в месяц."
    }
}