package com.example.apppractice.solid.calculator

// Principie (I)
interface IPSIElevateSquare {
    fun setNumElevate(num: Int): String
}

interface IPSIOpAritm {
    fun typeOperation(status: String): String
}

interface IPSIOpNot {
    fun statusUser(status: String): String
}