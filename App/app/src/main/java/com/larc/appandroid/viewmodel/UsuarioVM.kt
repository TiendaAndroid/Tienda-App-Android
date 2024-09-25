package com.larc.appandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larc.appandroid.model.LoginRequest
import com.larc.appandroid.model.ServicioRemotoUsuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsuarioVM: ViewModel() {
    // Modelo
    private val servicioRemotoUsuario = ServicioRemotoUsuario()

    //-------------------------------------------------------------------------------------
    // Estado
    private val _estdoToken = MutableStateFlow("")
    val estadoToken: StateFlow<String> = _estdoToken
    private val _loggedUsuario = MutableStateFlow(false)
    val loggedUsuario: StateFlow<Boolean> = _loggedUsuario
    private val _errorLogin = MutableStateFlow(false)
    val errorLogin: StateFlow<Boolean> = _errorLogin

    //-------------------------------------------------------------------------------------
    // Interface para la vista

    fun loginUser(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        resetErrorLogin()
        viewModelScope.launch {
            val result = servicioRemotoUsuario.loginUser(loginRequest)
            if (result != null) {
                _estdoToken.value = result.token
                _loggedUsuario.value = true
            } else {
                _estdoToken.value = ""
                _loggedUsuario.value = false
                _errorLogin.value = true
            }
        }
    }
    private fun resetErrorLogin() {
        _errorLogin.value = false
    }
}
