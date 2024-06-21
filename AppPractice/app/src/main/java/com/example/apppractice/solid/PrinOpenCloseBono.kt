package com.example.apppractice.solid


// Open close
// Las clases deben estar abiertas a agregar nuevas funciones(extenciones), pero no a ser
// modificadas(close), para esto son utiles las interfaces

class PrinOpenCloseBonoPunt : IPrinOpenCloseBono {

    override fun calculateBono(nomWorker: String?, days: Int): String {
         val calcBono = days * 100
         return String().plus(nomWorker).plus(" ").plus(calcBono)
    }
}

class PrinOpenCloseBonoProd : IPrinOpenCloseBono {

    override fun calculateBono(nomWorker: String?, days: Int): String {
         val calcBono = days * 300
         return String().plus(nomWorker).plus(" ").plus(calcBono)
    }
}
