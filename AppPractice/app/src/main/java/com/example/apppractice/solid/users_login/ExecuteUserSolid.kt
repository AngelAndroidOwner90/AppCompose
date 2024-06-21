package com.example.apppractice.solid.users_login

import android.content.Context

// Main
class ExecuteUserSolid {

    companion object  {

        @JvmStatic fun main(args : Array<String>) {

            uiSaveUser()
            uiGetAllUsers()
            uiUpdateStatus()
        }

        // Single responsibility (S)
        private fun uiSaveUser() {

            val uSave = SaveNewUser()
            val result = uSave.saveUserDB("Angel", "angel123")

            println("Usuario: ${uSave.setMsgEvent("success", result)}")
            println("Usuario: ${uSave.showTypeUser("Verificado")}")
            println("Usuario: ${uSave.statusUser("Activo")}")
        }

        private fun uiGetAllUsers() {
            val getUsers = GetAllUsers()
            println("Usuarios: ${getUsers.getUsersDB()}")
        }

        // Inversion dependences (D)
        private fun uiUpdateStatus() {
            val iStatus: IUserInverDepends = UpdateUserStatus()
            println("Usuarios: ${DeterminateUserUpdate(iStatus).setValuesStatus(50)}")
        }
    }
}

