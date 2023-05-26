package com.example.database.DataBase

import android.content.Context
import com.example.database.ContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserRepository {
    companion object {
        private var userDatabase: UserDataBase? = null

        private fun getDb(context: Context): UserDataBase? {
            return UserDataBase.getDatabase(context)
        }

        fun insert(user: UserEntity) {
            userDatabase = getDb(ContextProvider.getContext())

            CoroutineScope(IO).launch {
                userDatabase!!.userDao().insertUser(user)
            }
        }

        fun updateUsers(user: UserEntity) {
            userDatabase = getDb(ContextProvider.getContext())

            CoroutineScope(IO).launch {
                userDatabase!!.userDao().updateUsers(user)
            }
        }

        fun deleteUsers(name: String) {
            userDatabase = getDb(ContextProvider.getContext())

            CoroutineScope(IO).launch {
                userDatabase!!.userDao().deleteUsers(name)
            }
        }

        suspend fun searchUser(name: String): List<UserEntity> {
            return withContext(IO) {
                val userDatabase = getDb(ContextProvider.getContext())
                userDatabase!!.userDao().searchUser(name)
            }
        }

        suspend fun getAllUserData(): List<UserEntity> {
            return withContext(IO) {
                val userDatabase = getDb(ContextProvider.getContext())
                userDatabase!!.userDao().getAllUser()
            }
        }
    }
}