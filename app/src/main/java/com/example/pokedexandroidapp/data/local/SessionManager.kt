package com.example.pokedexandroidapp.data.local

import android.content.Context
import android.content.SharedPreferences

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences("user_session", Context.MODE_PRIVATE)

    fun saveEmail(email: String) {
        prefs.edit().putString("email", email).apply()
    }

    fun getEmail(): String? {
        return prefs.getString("email", null)
    }

    fun clear() {
        prefs.edit().clear().apply()
    }

    fun saveName(name: String) {
        prefs.edit().putString("name", name).apply()
    }

    fun getName(): String? {
        return prefs.getString("name", null)
    }
}
