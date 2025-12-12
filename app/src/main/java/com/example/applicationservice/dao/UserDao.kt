package com.example.applicationservice.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.applicationservice.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun registerUser(user: User)

    @Query("SELECT * FROM users WHERE email = :email AND password = :password")
    suspend fun login(email: String, password: String): User?

    @Query("SELECT * FROM users WHERE is_logged_in = 1 LIMIT 1")
    suspend fun getActiveUser(): User?

    @Update
    suspend fun updateUser(user: User)

    @Query("SELECT EXISTS(SELECT * FROM users WHERE email = :email)")
    suspend fun isEmailExists(email: String): Boolean
}