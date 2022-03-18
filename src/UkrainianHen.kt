package main

class UkrainianHen(): Hen(){
    val name_country = "Украина"
    override fun getCountOfEggsPerMonth(): Int {return 21}
    override fun getDescription(): String{
        return "Моя страна - ${name_country}. Я несу ${getCountOfEggsPerMonth()} яиц в месяц."
    }
}