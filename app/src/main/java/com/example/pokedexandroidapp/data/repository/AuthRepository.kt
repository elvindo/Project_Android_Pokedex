package com.example.pokedexandroidapp.data.repository

import android.content.Context
import android.provider.ContactsContract
import com.example.pokedexandroidapp.data.local.UserDatabase
import com.example.pokedexandroidapp.data.local.entity.User

class AuthRepository(context: Context) {
    private val userDao = UserDatabase.getDatabase(context).userDao()

    suspend fun register(user: User): Boolean {
        val existingUser = userDao.getUserByEmail(user.email)
        return if (existingUser == null) {
            userDao.insertUser(user)
            true

        } else {

            false
        }
    }
    suspend fun login(email: String, password: String): User? {
        return userDao.login(email, password)
    }
}