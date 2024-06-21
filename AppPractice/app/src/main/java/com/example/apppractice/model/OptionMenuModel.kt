package com.example.apppractice.model

import androidx.room.Ignore

data class OptionMenuModel(
    var idBtnMenu: Int? = null,
    var txtBtnMenu: String
){
    constructor() : this(null,"")
}
