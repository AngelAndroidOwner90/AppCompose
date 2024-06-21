package com.example.apppractice.solid.users_login

object Utilsdb {

    val listUsers: MutableList<UsersModel> = mutableListOf()

    fun saveUser(user: String, password: String): Int {

        val sizeX = listUsers.size
        var statusDB = 0

        if (user.isNotEmpty() && password.isNotEmpty()){
            listUsers.add(UsersModel("0", user, password))

            statusDB = if (listUsers.size > sizeX) {
                1
            }else{
                -1
            }
        }
        return statusDB
    }
}