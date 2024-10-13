package com.larc.appandroid.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larc.appandroid.MyApp
import com.larc.appandroid.model.LoginRequest
import com.larc.appandroid.model.Order
import com.larc.appandroid.model.RegisterRequest
import com.larc.appandroid.model.ServicioRemotoUsuario
import com.larc.appandroid.model.SignupRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Representa el viewmodel relacionado con el usuario.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

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
    private val _ordersList = MutableStateFlow<List<Order>>(emptyList())
    val ordersList: StateFlow<List<Order>> = _ordersList
    private val _personas = MutableStateFlow(0)
    val personas: StateFlow<Int> = _personas
    private val _kilogramos = MutableStateFlow(0)
    val kilogramos: StateFlow<Int> = _kilogramos
    private val _ahorro = MutableStateFlow(0)
    val ahorro: StateFlow<Int> = _ahorro

    //-------------------------------------------------------------------------------------
    // Interface para la vista

    /**
     * Inicia sesión para un usuario.
     *
     * Envía una solicitud de inicio de sesión con el correo y contraseña del usuario. Si la operación es exitosa,
     * se guarda el token de autenticación y se actualiza el estado para indicar que el usuario está autenticado.
     *
     * @param email El correo electrónico del usuario.
     * @param password La contraseña del usuario.
     */
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

    /**
     * Solicita el envío de un correo electrónico para el registro de un nuevo usuario.
     *
     * Envía una solicitud con la información del usuario (nombre, apellido, correo y contraseña) para que se le
     * envíe un correo de confirmación con un token de registro.
     *
     * @param email El correo electrónico del usuario.
     * @param name El nombre del usuario.
     * @param lastName El apellido del usuario.
     * @param password La contraseña del usuario.
     */
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

    /**
     * Completa el registro de un usuario utilizando el token de verificación.
     *
     * Envía una solicitud con la información del usuario y el token de verificación para completar el registro.
     * Si la operación es exitosa, se guarda el token de autenticación y se actualiza el estado del usuario.
     *
     * @param email El correo electrónico del usuario.
     * @param name El nombre del usuario.
     * @param lastName El apellido del usuario.
     * @param password La contraseña del usuario.
     * @param token El token de verificación recibido por correo.
     * @param birthDay La fecha de nacimiento del usuario.
     * @param phone El número de teléfono del usuario.
     */
    fun registerUSer(email: String, name: String, lastName: String, password: String, token: String, birthDay: String, phone: String) {
        _errorRegister.value = false
        val registerRequest = RegisterRequest(email, name, lastName, password, token, birthDay, phone)
        viewModelScope.launch {
            val result = servicioRemotoUsuario.registerUser(registerRequest)
            if (result != null) {
                _estadoToken.value = result.token
                _loggedUsuario.value = true
                saveToken(result.token)
                _errorRegister.value = false
                _registroExitoso.value = true
                Log.d("Registro:", "Registro exitoso")
            } else {
                _estadoToken.value = ""
                _loggedUsuario.value = false
                _errorRegister.value = true
                _registroExitoso.value = false
                Log.d("Registro:", "Registro fallido")
            }
        }
    }

    /**
     * Cierra sesión del usuario actual.
     *
     * Limpia el token de autenticación almacenado y actualiza el estado para indicar que el usuario no está autenticado.
     */
    fun logoutUser() {
        viewModelScope.launch {
            clearToken()
            _estadoToken.value = ""
            _loggedUsuario.value = false
        }
    }

    /**
     * Restablece el estado de error del inicio de sesión.
     */
    private fun resetErrorLogin() {
        _errorLogin.value = false
    }

    /**
     * Guarda el token de autenticación en SharedPreferences.
     *
     * @param token El token de autenticación a guardar.
     */
    private fun saveToken(token: String) {
        val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().putString("auth_token", token).apply()
    }

    /**
     * Elimina el token de autenticación almacenado en SharedPreferences.
     */
    private fun clearToken() {
        val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        sharedPreferences.edit().remove("auth_token").apply()
    }

    /**
     * Verifica si el usuario está autenticado.
     *
     * Comprueba si existe un token de autenticación guardado. Si lo hay, actualiza el estado para indicar
     * que el usuario está autenticado.
     */
    fun checkIfLoggedIn() {
        val token = getToken()
        if (token != null) {
            _estadoToken.value = token
            _loggedUsuario.value = true
        }
    }

    /**
     * Obtiene el token de autenticación almacenado.
     *
     * @return El token de autenticación almacenado o `null` si no existe.
     */
    fun getToken(): String? {
        val sharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("auth_token", null)
    }

    /**
     * Obtiene el perfil del usuario autenticado.
     *
     * Envía una solicitud para obtener los detalles del perfil del usuario utilizando el token de autenticación.
     * Actualiza el estado del perfil con la información recibida.
     */
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
                        birthDay = result.birthDay,
                        phoneNumber = result.phoneNumber,
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

    /**
     * Obtiene el ID del carrito del usuario autenticado.
     *
     * Llama a la función `getProfile` para asegurarse de que el perfil está actualizado y luego retorna el ID
     * del carrito del usuario.
     *
     * @return El ID del carrito del usuario o una cadena vacía si no se encuentra el carrito.
     */
    fun getCartId(): String {
        getProfile()
        return _estadoMiUsuario.value.cart?.id ?: ""
    }

    fun populateOrderList() {
        viewModelScope.launch {
            _ordersList.value = emptyList()
            if (estadoMiUsuario.value.orders != null) {
                val filteredOrders = estadoMiUsuario.value.orders!!.filter { it.status != "PENDING" }
                _ordersList.value = filteredOrders
            }
        }
    }

    fun sortOrderList() {
        _ordersList.value = _ordersList.value.sortedByDescending { it.createdAt }
    }

    fun calcularAyuda() {
        val valores =  _estadoMiUsuario.value.orders?.flatMap { order ->
            order.orderItems?.map { it.product.valor } ?: emptyList()
        }?.sum() ?: 0
        _personas.value = valores
        _kilogramos.value = ((valores/6)+1) * 29
        _ahorro.value = ((valores/6)+1) * 3750
    }

}
