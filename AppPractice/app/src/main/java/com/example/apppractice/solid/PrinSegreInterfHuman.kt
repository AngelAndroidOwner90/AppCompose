package com.example.apppractice.solid

// I
// Solo implementa la interface que utliza, para no implementar metodos inecesarios

// Hold man
class PrinSegreInterfOldMan : IPrinSegInterWalk, IPrinSegInterSleep {

    override fun goWalk() {
        println("Salir a caminar")
    }

    override fun goSleep() {
        println("Dormir a cualquier hora")
    }
}

// Young
class PrinSegreInterfYoung : IPrinSegInterWork, IPrinSegInterWalk, IPrinSegInterSleep {

    override fun goWork() {
        println("Salir a trabajar")
    }

    override fun goWalk() {
        println("Salir a caminar")
    }

    override fun goSleep() {
        println("Dormir por la noche")
    }
}

// Baby
class PrinSegreInterfBaby : IPrinSegInterSleep {
    override fun goSleep() {
        println("Dormir a cualquier hora")
    }
}