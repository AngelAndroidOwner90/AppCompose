package com.example.apppractice.solid


// D
// Class down level
class ControlLamp {

    fun turnOn(): Boolean {
        return true
    }

    fun turnOff(): Boolean {
        return false
    }
}

// Class alto level // Create instance
class Lamp {

    var cLamp: ControlLamp? = null

    init {
        this.cLamp = ControlLamp()
    }

    fun operateLamp() {
        if (cLamp?.turnOn() == true) {
            println("Lampara prendida")
        }else if (cLamp?.turnOff() == false) {
            println("Lampara apagada")
        }
    }
}

// D
// Using solid (Inversion dependency)
// Class down level
class ControlLampID : IControlLampInvDepen {

    override fun turnOn(event: Boolean): Boolean {
        return event
    }

    override fun turnOff(event: Boolean): Boolean {
        return event
    }
}

// Class alto level // Inject instance
class LampID {

    var cLamp: IControlLampInvDepen? = null

    constructor(cLamp: IControlLampInvDepen? = null) {
        this.cLamp = cLamp
    }

    fun operateLamp(event: Boolean) {
        if (cLamp?.turnOn(event) == true) {
            println("Lampara prendida")
        }else if (cLamp?.turnOff(event) == false) {
            println("Lampara apagada")
        }
    }
}




