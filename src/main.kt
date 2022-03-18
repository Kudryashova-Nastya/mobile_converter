package main

fun main() {
    val string = Str()
    string.stringSize("О мой бог вы тоже тут")
    string.stringConcatenate("Загадка ", "от ", "Жака ", "Фреско")
    string.stringAdd("кушать")
    string.stringDel("Я не люблю вставать на пары в 6 утра", "не ")
    string.stringUppercase(true,"да не бомбит у меня")
    string.stringUppercase(false,"ДеВаЧкИ, вЫ уПаЛи")
}

class Str() {
    fun stringSize(str: String) {
        println("Размер строки '$str': ${str.length}")
    }

    fun stringConcatenate(vararg strings: String){
        var result=""
        for(str in strings)
            result += str
        println("Конкатенация строк: $result")
    }

    fun stringAdd(str: String) {
        val start = "Я очень люблю "
        println("$start$str")
    }

    fun stringDel(str: String, del: String) {
        println(str.replace(del, ""))
    }

    fun stringUppercase(up: Boolean, str: String) {
        if (up) {
            println(str.uppercase())
        }
        else {
            println(str.lowercase())
        }
    }
}