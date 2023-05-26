package com.example.database.DataBase

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "UserEntity")
data class UserEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_name") val userName: String = "temp",
    @ColumnInfo(name = "activity") val userActivity: String?,
    @ColumnInfo(name = "is_complete") val isComplete:Boolean,
)