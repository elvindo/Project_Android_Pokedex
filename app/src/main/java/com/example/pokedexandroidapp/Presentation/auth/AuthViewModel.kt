package com.example.pokedexandroidapp.Presentation.auth

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedexandroidapp.data.local.SessionManager
import com.example.pokedexandroidapp.data.local.entity.User
import com.example.pokedexandroidapp.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(context: Context) : ViewModel() {
    private val repository = AuthRepository(context)

    private val _loginSuccess = MutableStateFlow<Boolean?>(null)
    val loginSuccess: StateFlow<Boolean?> = _loginSuccess

    private val _registerSuccess = MutableStateFlow<Boolean?>(null)
    val registerSuccess: StateFlow<Boolean?> = _registerSuccess

    fun register(context: Context, name: String, email: String, password: String) {
        viewModelScope.launch {
            val user = User(name = name, email = email, password = password)
            val result = repository.register(user)
            if (result) {
                SessionManager(context).apply {
                    saveName(user.name)
                    saveEmail(user.email)
                }
            }
            _registerSuccess.value = result
        }
    }

    fun login(context: Context, email: String, password: String) {
        viewModelScope.launch {
            val user = repository.login(email, password)
            if (user != null) {
                SessionManager(context).apply {
                    saveName(user.name)
                    saveEmail(user.email)
                }
            }
            _loginSuccess.value = user != null
        }
    }
}
