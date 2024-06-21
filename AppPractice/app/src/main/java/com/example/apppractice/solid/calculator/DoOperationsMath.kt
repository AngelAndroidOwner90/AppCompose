package com.example.apppractice.solid.calculator

    // Principie (S)
    open class OperationAdd : IPrinOpenCloseCalc {
        override fun calculateOperation(num1: Int, num2: Int): String {
            val result = (num1 + num2)
            return result.toString()
        }
    }

    open class OperationMinus : IPrinOpenCloseCalc {
        override fun calculateOperation(num1: Int, num2: Int): String {
            val result = (num1 - num2)
            return result.toString()
        }
    }

    open class OperationDiv : IPrinOpenCloseCalc {
        override fun calculateOperation(num1: Int, num2: Int): String {
            val result = (num1.toFloat() / num2.toFloat())
            return result.toString()
        }
    }

    open class OpElevateSquare : IPSIElevateSquare {
        override fun setNumElevate(num: Int): String {
            val result = (num * num)
            return result.toString()
        }
    }

    // Class height level
    open class CheckIfNumIsPositive : IPrinInverDependsCalc {
        override fun numPositive(num: Int): String {
            var msgNum = ""
            if (num > 0) {
                msgNum = "Numero positivo"
            }
            return msgNum
        }

        override fun numNegative(num: Int): String {
            var msgNum = ""
            if (num <= 0) {
                msgNum = "Numero negativo"
            }
            return msgNum
        }
    }

    // Class height level
    class DeterminateUserUpdate(private var iNumCalc: IPrinInverDependsCalc?) {

        // Using builder -> constructor
        /*var uStatus: IUserInverDepends? = null

        constructor(uStatus: IUserInverDepends?) {
            this.uStatus = uStatus
        }*/

        /*fun getTypeNumber(num: Int, check: Boolean): String {

            var tStatus = ""

            if (check) {
                tStatus = uStatus?.enabledUserPro(nDodts)?: "Not"
            }else{
                tStatus = uStatus?.disableUserPro(nDodts)?: "Not"
            }
            return tStatus
        }*/
    }
