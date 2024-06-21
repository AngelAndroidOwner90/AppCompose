package com.example.apppractice.data

import com.example.apppractice.dbroom.LogUserQueryDao
import com.example.apppractice.model.LogUserEntyModel
import com.example.apppractice.model.OptionMenuModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

// Repository
class LogUserRepository @Inject constructor(private val productDao: LogUserQueryDao) {

    suspend fun addNewUserRepo(nUser: LogUserEntyModel) {
        productDao.addNewUser(nUser)
    }

    fun getAllUsersRepo(): List<LogUserEntyModel> {
        return productDao.getAllUsers()
    }

    fun loginInUserRepo(nUser: String, nPassword: String): Flow<LogUserEntyModel> {
       return productDao.loginInUser(nUser, nPassword)
    }

    /*suspend fun updateProductRepo(uProduct: LogUserEntyModel) {
        productDao.updateProduct(uProduct)
    }

    suspend fun searchProductByIdRepo(id: Int): List<LogUserEntyModel> {
        return productDao.searchProductById(id)
    }

    suspend fun deleteProductIdRepo(id: Int) {
        productDao.deleteProductById(id)
    }*/
}