package com.example.apppractice.solid.users_login

// Inversion de dependencias (D)
interface IUserInverDepends {

    fun enabledUserPro(dots: Int): String
    fun disableUserPro(dots: Int): String
}