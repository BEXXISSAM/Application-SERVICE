package com.example.applicationservice.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.applicationservice.models.User
import com.example.applicationservice.repositories.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: UserRepository) : ViewModel() {

    private val _userState = MutableStateFlow<User?>(null)
    val userState: StateFlow<User?> = _userState

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun login(email: String, pass: String) {
        viewModelScope.launch {
            val user = repository.login(email, pass)
            if (user != null) {
                _userState.value = user
                _errorMessage.value = null
            } else {
                _errorMessage.value = "Email ou mot de passe incorrect"
            }
        }
    }

    fun register(fullName: String, email: String, pass: String) {
        viewModelScope.launch {
            try {
                val newUser = User(email = email, fullName = fullName, password = pass)
                repository.register(newUser)
                login(email, pass)
            } catch (e: Exception) {
                _errorMessage.value = "Erreur: Cet email existe peut-être déjà."
            }
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
            _userState.value = null
        }
    }

    fun checkActiveSession() {
        viewModelScope.launch {
            _userState.value = repository.getActiveUser()
        }
    }
}