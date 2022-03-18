package main

fun main() {
    val belHen = BelarusianHen()
    println(belHen.getDescription())

    val rusHen: Hen? = HenFactory().getHen("Россия")
    println(rusHen?.getDescription())
}