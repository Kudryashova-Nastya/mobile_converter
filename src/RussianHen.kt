package main

class RussianHen(): Hen(){
    val name_country = "Россия"
    override fun getCountOfEggsPerMonth(): Int {return 19}
    override fun getDescription(): String {
        return "Моя страна - ${name_country}. Я несу ${getCountOfEggsPerMonth()} яиц в месяц."
    }
}