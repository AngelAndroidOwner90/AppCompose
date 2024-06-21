package com.example.apppractice.dbroom

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomWarnings
import androidx.room.Transaction
import com.example.apppractice.model.LogUserEntyModel
import com.example.apppractice.model.OptionMenuModel
import kotlinx.coroutines.flow.Flow

// Table
@Dao
interface LogUserQueryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addNewUser(nProduct: LogUserEntyModel)

    @Query("SELECT * FROM users_tab ORDER BY idUser ASC")
    fun getAllUsers(): List<LogUserEntyModel>

    @Query("SELECT * FROM users_tab WHERE nameUser LIKE :nUser AND password LIKE :nPassword")
    fun loginInUser(nUser: String, nPassword: String): Flow<LogUserEntyModel>

}