fun main() {
    val belHen: BelarusianHen = BelarusianHen()
    println(belHen.getDescription())

    val rusHen: Hen? = HenFactory().getHen("Россия")
    println(rusHen?.getDescription())
}

abstract class Hen(){
    abstract fun getCountOfEggsPerMonth(): Int
    open fun getDescription(): String {return "Я курица."}
}

class RussianHen(): Hen(){
    val name_country = "Россия"
    override fun getCountOfEggsPerMonth(): Int {return 19}
    override fun getDescription(): String {
        return "Моя страна - ${name_country}. Я несу ${getCountOfEggsPerMonth()} яиц в месяц."
    }
}
class UkrainianHen(): Hen(){
    val name_country = "Украина"
    override fun getCountOfEggsPerMonth(): Int {return 21}
    override fun getDescription(): String{
        return "Моя страна - ${name_country}. Я несу ${getCountOfEggsPerMonth()} яиц в месяц."
    }
}
class MoldovanHen(): Hen(){
    val name_country = "Молдова"
    override fun getCountOfEggsPerMonth(): Int {return 25}
    override fun getDescription(): String{
        return "Моя страна - ${name_country}. Я несу ${getCountOfEggsPerMonth()} яиц в месяц."
    }
}
class BelarusianHen(): Hen(){
    val name_country = "Беларусь"
    override fun getCountOfEggsPerMonth(): Int {return 18}
    override fun getDescription(): String{
        return "Моя страна - ${name_country}. Я несу ${getCountOfEggsPerMonth()} яиц в месяц."
    }
}

class HenFactory() {
    fun getHen(country: String): Hen? {
        val map = mapOf("Россия" to RussianHen(), "Украина" to UkrainianHen(),
            "Молдова" to MoldovanHen(), "Беларусь" to BelarusianHen())
        return map[country]
    }
}