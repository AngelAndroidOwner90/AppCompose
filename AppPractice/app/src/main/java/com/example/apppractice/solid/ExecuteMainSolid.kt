package com.example.apppractice.solid

import android.content.Context

// Main
class ExecuteMainSolid {

    companion object  {

        @JvmStatic fun main(args : Array<String>) {

            calculateSalary()
            calculateHourExtra()
            calculateBono()
            checkSpeedBicycle()
            checkActivityHuman()
            statusLamp()
        }

        // Single responsibility (S)
        private fun calculateSalary() {
            val salary = SingleRestyCalculateSalary()
            println("Salario: ${salary.getDaysWorkeds(6)}")
        }

        private fun calculateHourExtra() {
            val extra = SingleRestyHoursExtra()
            println("Extra: ${extra.getNumHourExtra(10)}")
        }

        // Open close (O)
        private fun calculateBono() {
            val bonoPt = PrinOpenCloseBonoPunt()
            println("Bono punt: ${bonoPt.calculateBono("Juan", 5)}")

            val bonoPd = PrinOpenCloseBonoProd()
            println("Bono prod: ${bonoPd.calculateBono("Pancho", 4)}")
        }

        // Substitution Liskov (L)
        private fun checkSpeedBicycle() {
            PrinSubLisBicycleMountain().runBicycle()
            PrinSubLisBicycleCareers().runBicycle()
        }

        // Segregation of interfaz (I)
        private fun checkActivityHuman() {
            PrinSegreInterfOldMan().goWalk()
            PrinSegreInterfOldMan().goSleep()

            PrinSegreInterfYoung().goWalk()
            PrinSegreInterfYoung().goSleep()
            PrinSegreInterfYoung().goWalk()

            PrinSegreInterfBaby().goSleep()
        }

        // Inversion of dependency (D)
        private fun statusLamp() {

            val iControl: IControlLampInvDepen = ControlLampID()
            LampID(iControl).operateLamp(false)
        }
    }
}

