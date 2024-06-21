package com.example.apppractice.solid

// L
class PrinSubLisBicycleMountain : PrinSubstLiskovAbst() {
    override fun runBicycle() {
        println("Run with 10 speeds")
    }
}

class PrinSubLisBicycleCareers : PrinSubstLiskovAbst() {
    override fun runBicycle() {
        println("Run with 1 speeds")
    }
}