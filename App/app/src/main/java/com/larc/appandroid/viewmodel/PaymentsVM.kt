package com.larc.appandroid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larc.appandroid.model.PaymentIntentResponse
import com.larc.appandroid.model.PaymentSessionDto
import com.larc.appandroid.model.PaymentsAPI
import com.larc.appandroid.model.ServicioRemotoPayments
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class PaymentsVM : ViewModel() {

    // Instance of the remote service
    private val servicioRemotoPayments = ServicioRemotoPayments()

    private val _clientSecret = MutableStateFlow<String?>(null)
    val clientSecret: StateFlow<String?> = _clientSecret

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _estadoDireccionEntrega = MutableStateFlow( EstadoDireccionEntrega() )
    val estadoDireccionEntrega: StateFlow<EstadoDireccionEntrega> = _estadoDireccionEntrega

    /**
     * Crea un PaymentIntent usando el servicio remoto.
     *
     * Llama al servicio remoto para generar un PaymentIntent y obtener el clientSecret
     * que será usado para confirmar el pago con Stripe.
     *
     * @param token El token de autorización del usuario.
     * @param currency La moneda de la transacción (ej: "usd").
     */
    fun createPaymentIntent(
        token: String?,
        currency: String,
        tipo: String,
        pais: String,
        municipio: String,
        estado: String,
        calle: String,
        noExterior: String,
        colonia: String,
        cp: Int
    ) {
        _isLoading.value = true
        _error.value = null

        if (token == null) {
            _error.value = "Error: Token is null."
            return
        } else {
            viewModelScope.launch {
                try {
                    val paymentSessionDto = PaymentSessionDto(
                        currency = currency,
                        tipo = tipo,
                        pais = pais,
                        municipio = municipio,
                        estado = estado,
                        calle = calle,
                        noExterior = noExterior,
                        noInterior = "",
                        colonia = colonia,
                        cp = cp
                    )
                    val result =
                        servicioRemotoPayments.createPaymentIntent(token, paymentSessionDto)
                    if (result != null) {
                        _clientSecret.value = result.clientSecret
                    } else {
                        _error.value = "Error: Unable to fetch client secret."
                    }
                } catch (e: Exception) {
                    _error.value = "Exception: ${e.message}"
                } finally {
                    _isLoading.value = false
                }
            }
        }
    }
    fun resetError() {
        _error.value = null
    }
    fun updateEstadoDireccionEntrega(
        tipo: String,
        pais: String,
        municipio: String,
        estado: String,
        calle: String,
        noExterior: String,
        noInterior: String?,
        colonia: String,
        cp: Int
    ) {
        viewModelScope.launch {
            _estadoDireccionEntrega.value = _estadoDireccionEntrega.value.copy(
                tipo = tipo,
                pais = pais,
                municipio = municipio,
                estado = estado,
                calle = calle,
                noExterior = noExterior,
                noInterior = noInterior,
                colonia = colonia,
                cp = cp
            )
        }
    }
}
