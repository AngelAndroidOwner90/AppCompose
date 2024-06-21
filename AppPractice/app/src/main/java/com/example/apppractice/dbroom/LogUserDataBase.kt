package com.example.apppractice.dbroom

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.apppractice.model.LogUserEntyModel

// Data base
@Database(entities = [LogUserEntyModel::class], version = 1, exportSchema = false)
//@TypeConverters(ConvertersImg::class)
abstract class LogUserDataBase: RoomDatabase() {

    abstract fun userDao(): LogUserQueryDao
}