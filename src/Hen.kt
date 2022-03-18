package main

abstract class Hen(){
    abstract fun getCountOfEggsPerMonth(): Int
    open fun getDescription(): String {return "Я курица."}
}