package com.example.apppractice.solid.users_login

// Principie open close
interface IPrinOpenCloseUser {

    fun setMsgEvent(msg: String?, codeStatus: Int): String
}