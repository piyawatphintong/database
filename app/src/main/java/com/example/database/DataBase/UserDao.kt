package com.example.database.DataBase

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg userData:UserEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUsers(vararg users: UserEntity)

    @Query("DELETE FROM UserEntity WHERE user_name =:userName")
    fun deleteUsers(vararg userName: String)

    @Query("SELECT * FROM UserEntity WHERE user_name =:userName")
    fun searchUser(userName:String):List<UserEntity>

    @Query("SELECT * FROM UserEntity")
    fun getAllUser():List<UserEntity>
}