package com.example.apppractice.solid.calculator

import android.content.Context

// Main
class ExecuteCalcSolid() {

    companion object  {

        @JvmStatic fun main(args : Array<String>) {
            uiOpAdd()
            uiOpMinus()
            uiOpSquare()
            uiOpDiv()
        }

        // Single responsibility (S)
        private fun uiOpAdd() {
            val oAdd = OperationAdd()
            val result = oAdd.calculateOperation(10, 20)
            println("Suma: $result")
        }

        private fun uiOpMinus() {
            val oAdd = OperationMinus()
            val result = oAdd.calculateOperation(10, 20)
            println("Resta: $result")
        }

        private fun uiOpDiv() {
            println("Division: ${OperationDiv().calculateOperation(10, 3)}")
        }

        private fun uiOpSquare() {
            println("Cuadrado: ${OpElevateSquare().setNumElevate(10)}")
        }

        /*// Inversion dependences (D)
        private fun uiUpdateStatus() {
            val iStatus: IUserInverDepends = UpdateUserStatus()
            println("Usuarios: ${DeterminateUserUpdate(iStatus).setValuesStatus(50)}")
        }*/
    }
}

