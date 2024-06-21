package com.example.apppractice.solid.users_login

// Single responsibility
// Cada clase debe realizar una sola tarea, no debe hacer una clase tareas de diferente tipo

class SaveNewUser: IPrinOpenCloseUser, IpsiTypeUser, IpsiStatusUser {

    fun saveUserDB(nUser: String, nPassword: String): Int {
        return Utilsdb.saveUser(user = nUser, nPassword)
    }

    override fun setMsgEvent(msg: String?, codeStatus: Int): String {

        var status: String = ""

        status = if (msg == "success" && codeStatus == 1) {
            "Registro exitoso!"
        }else{
            "Registro fayido!"
        }
        return status
    }

    override fun showTypeUser(type: String): String {
        return "Tipo de usuario: $type"
    }

    override fun statusUser(status: String): String {
        return "Status del usuario: $status"
    }
}

class LoginUser {
    fun getNumHourExtra(hours: Int): Int {
        return hours * 50
    }
}

class ChangePasswordUser {
    fun getNumHourExtra(hours: Int): Int {
        return hours * 50
    }
}

class GetAllUsers {
    fun getUsersDB(): MutableList<UsersModel> {
        return Utilsdb.listUsers
    }
}

class DeleteUser {
    fun getNumHourExtra(hours: Int): Int {
        return hours * 50
    }
}

class UpdateUser {
    fun getNumHourExtra(hours: Int): Int {
        return hours * 50
    }
}

// Class down level
class UpdateUserStatus(): IUserInverDepends {

    override fun enabledUserPro(dots: Int): String {

        var msgStatus = ""

        if (dots >= 100) {
            msgStatus = "Usuario ***PREMOUM***"
        }
        return msgStatus
    }

    override fun disableUserPro(dots: Int): String {
        var msgStatus = ""

        if (dots < 100) {
            msgStatus = "Usuario ***BASICO***"
        }
        return msgStatus
    }
}

// Class height level
class DeterminateUserUpdate(private var uStatus: IUserInverDepends?) {

    // Using builder -> constructor
    /*var uStatus: IUserInverDepends? = null

    constructor(uStatus: IUserInverDepends?) {
        this.uStatus = uStatus
    }*/

    fun setValuesStatus(nDodts: Int): String {

        var tStatus = ""

        if (nDodts >= 100) {
            tStatus = uStatus?.enabledUserPro(nDodts)?: "Not"
        }else{
            tStatus = uStatus?.disableUserPro(nDodts)?: "Not"
        }
        return tStatus
    }
}