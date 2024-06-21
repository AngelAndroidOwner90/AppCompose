package com.example.apppractice.solid.calculator

// Inversion de dependencias (D)
interface IPrinInverDependsCalc {

    fun numPositive(num: Int): String
    fun numNegative(num: Int): String
}