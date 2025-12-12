package com.example.applicationservice.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey
    @ColumnInfo(name = "email")
    val email: String,

    @ColumnInfo(name = "fullName")
    val fullName: String,

    @ColumnInfo(name = "password")
    val password: String,

    @ColumnInfo(name = "is_logged_in")
    var isLoggedIn: Boolean = false
)
