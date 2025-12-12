package com.example.applicationservice.repositories

import com.example.applicationservice.dao.UserDao
import com.example.applicationservice.models.User

class UserRepository(private val userDao: UserDao) {

    suspend fun register(user: User) {
        userDao.registerUser(user)
    }

    suspend fun login(email: String, pass: String): User? {
        val user = userDao.login(email, pass)
        if (user != null) {
            user.isLoggedIn = true
            userDao.updateUser(user)
        }
        return user
    }

    suspend fun logout() {
        val activeUser = userDao.getActiveUser()
        if (activeUser != null) {
            activeUser.isLoggedIn = false
            userDao.updateUser(activeUser)
        }
    }

    suspend fun getActiveUser(): User? {
        return userDao.getActiveUser()
    }
}