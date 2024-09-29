package com.larc.appandroid.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larc.appandroid.MyApp
import com.larc.appandroid.model.LoginRequest
import com.larc.appandroid.model.RegisterRequest
import com.larc.appandroid.model.ServicioRemotoUsuario
import com.larc.appandroid.model.SignupRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsuarioVM: ViewModel() {

    private val context = MyApp.instance.applicationContext

    // Modelo
    private val servicioRemotoUsuario = ServicioRemotoUsuario()

    //-------------------------------------------------------------------------------------
    // Estado
    private val _estadoToken = MutableStateFlow("")
    val estadoToken: StateFlow<String> = _estadoToken
    private val _loggedUsuario = MutableStateFlow(false)
    val loggedUsuario: StateFlow<Boolean> = _loggedUsuario
    private val _errorLogin = MutableStateFlow(false)
    val errorLogin: StateFlow<Boolean> = _errorLogin
    private val _mailEnviado = MutableStateFlow(false)
    val mailEnviado: StateFlow<Boolean> = _mailEnviado
    private val _errorSendMail = MutableStateFlow(false)
    val errorSendMail: StateFlow<Boolean> = _errorSendMail
    private val _errorRegister = MutableStateFlow(false)
    val errorRegister: StateFlow<Boolean> = _errorRegister
    private val _registroExitoso = MutableStateFlow(false)
    val registroExitoso: StateFlow<Boolean> = _registroExitoso
    private val _estadoMiUsuario = MutableStateFlow( EstadoUsuario() )
    val estadoMiUsuario: StateFlow<EstadoUsuario> = _estadoMiUsuario

    //-------------------------------------------------------------------------------------
    // Interface para la vista

    fun loginUser(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        resetErrorLogin()
        viewModelScope.launch {
            val result = servicioRemotoUsuario.loginUser(loginRequest)
            if (result != null) {
                _estadoToken.value = result.token
                _loggedUsuario.value = true
                saveToken(result.token)
            } else {
                _estadoToken.value = ""
                _loggedUsuario.value = false
                _errorLogin.value = true
            }
        }
    }
    fun registerSendMail(email: String, name: String, lastName: String, password: String) {
        _errorSendMail.value = false
        val signupRequest = SignupRequest(email, name, lastName, password)
        viewModelScope.launch {
            val result = servicioRemotoUsuario.signupEmail(signupRequest)
            if (result != null) {
                _mailEnviado.value = true
                _errorSendMail.value = false
            } else {
                _mailEnviado.value = false
                _errorSendMail.value = true
            }
        }
    }
    fun registerUSer(email: String, name: String, lastName: String, password: String, token: String) {
        _errorRegister.value = false
        val registerRequest = RegisterRequest(email, name, lastName, password, token)
        viewModelScope.launch {
            val result = servicioRemotoUsuario.registerUser(registerRequest)
            if (result != null) {
                _estadoToken.value = result.token
                _loggedUsuario.value = true
                saveToken(result.token)
                _errorRegister.value = false
                _registroExitoso.value = true
            } else {
                _estadoToken.value = ""
                _loggedUsuario.value = false
                _errorRegister.value = true
                _registroExitoso.value = false
            }
        }
    }
    fun logoutUser() {
        viewModelScope.launch {
            clearToken()
            _estadoToken.value = ""
            _loggedUsuario.value = false
        }
    }
    private fun resetErrorLogin() {
        _errorLogin.value = false
    }

    private fun saveToken(token: String) {
        val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("auth_token", token).apply()
    }
    private fun clearToken() {
        val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().remove("auth_token").apply()
    }
    fun checkIfLoggedIn() {
        val token = getToken()
        if (token != null) {
            _estadoToken.value = token
            _loggedUsuario.value = true
        }
    }
    fun getToken(): String? {
        val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("auth_token", null)
    }
    fun getProfile() {
        if (getToken() != null) {
            viewModelScope.launch {
                val result = servicioRemotoUsuario.getProfile(getToken()!!)
                if (result != null) {
                    _estadoMiUsuario.value = _estadoMiUsuario.value.copy(
                        id = result.id,
                        email = result.email,
                        name = result.name,
                        lastName = result.lastName,
                        googleId = result.googleId,
                        isActive = result.isActive,
                        role = result.role,
                        direction = result.direction,
                        cart = result.cart,
                        orders = result.orders
                    )
                }
            }
        }
    }
}
