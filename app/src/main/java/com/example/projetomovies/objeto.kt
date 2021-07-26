package com.example.projetomovies

fun main() {
    val pets = arrayListOf<String>()
    pets.add("pretinho")
    pets.add("branca")
    pets.add("caramelo")
    pets.add("cinzento")

    for(pet in pets) println(pet)

    for(i in 12 downTo 0 step 2) println(i)

    var i = 0
    while(i<10){
        i++
        if(i%2 != 0) continue
        println("WHILE $i")
    }
}