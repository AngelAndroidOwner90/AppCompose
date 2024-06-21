package com.example.apppractice.solid

// Single responsibility
// Cada clase debe realizar una sola tarea, no debe hacer una clase tareas de diferente tipo

class SingleRestyCalculateSalary {
    fun getDaysWorkeds(days: Int): Int {
        return days * 150
    }
}

class SingleRestyHoursExtra {
    fun getNumHourExtra(hours: Int): Int {
        return hours * 50
    }
}