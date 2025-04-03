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

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun register(context: Context, name: String, email: String, password: String) {
        viewModelScope.launch {
            if (name.isBlank() || email.isBlank() || password.isBlank()) {
                _errorMessage.value = "Please fill in all fields."
                return@launch
            }

            val user = User(name = name, email = email, password = password)
            val result = repository.register(user)

            if (result) {
                SessionManager(context).apply {
                    saveName(user.name)
                    saveEmail(user.email)
                }
                _registerSuccess.value = true
            } else {
                _errorMessage.value = "Account with this email already exists."
                _registerSuccess.value = false
            }
        }
    }

    fun login(context: Context, email: String, password: String) {
        viewModelScope.launch {
            if (email.isBlank() || password.isBlank()) {
                _errorMessage.value = "Please fill in all fields."
                return@launch
            }

            val user = repository.login(email, password)
            if (user != null) {
                SessionManager(context).apply {
                    saveName(user.name)
                    saveEmail(user.email)
                }
                _loginSuccess.value = true
            } else {
                _errorMessage.value = "Invalid email or password."
                _loginSuccess.value = false
            }
        }
    }

    fun clearErrorMessage() {
        _errorMessage.value = null
    }



}
