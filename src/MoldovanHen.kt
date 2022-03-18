package main

class MoldovanHen(): Hen(){
    val name_country = "Молдова"
    override fun getCountOfEggsPerMonth(): Int {return 25}
    override fun getDescription(): String{
        return "Моя страна - ${name_country}. Я несу ${getCountOfEggsPerMonth()} яиц в месяц."
    }
}