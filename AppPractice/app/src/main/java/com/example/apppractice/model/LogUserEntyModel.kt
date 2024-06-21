package com.example.apppractice.model

import android.graphics.Bitmap
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users_tab")
data class LogUserEntyModel(
    @PrimaryKey(autoGenerate = true)
    var idUser: Int? = null,
    var nameUser: String? = null,
    var password: String? = null
) {
    constructor() : this(null,"","")

}