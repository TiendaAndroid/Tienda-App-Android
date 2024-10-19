package com.larc.appandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larc.appandroid.model.PaymentSessionDto
import com.larc.appandroid.model.ServicioRemotoPayments
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Representa el viewmodel relacionado con los pagos.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
class PaymentsVM : ViewModel() {

    // Modelo
    private val servicioRemotoPayments = ServicioRemotoPayments()

    //-------------------------------------------------------------------------------------
    // Estado
    private val _clientSecret = MutableStateFlow<String?>(null)
    val clientSecret: StateFlow<String?> = _clientSecret
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error
    private val _estadoDireccionEntrega = MutableStateFlow( EstadoDireccionEntrega() )
    val estadoDireccionEntrega: StateFlow<EstadoDireccionEntrega> = _estadoDireccionEntrega

    //-------------------------------------------------------------------------------------
    // Interface para la vista

    /**
     * Crea un PaymentIntent usando el servicio remoto.
     *
     * Llama al servicio remoto para generar un PaymentIntent y obtener el clientSecret
     * que será usado para confirmar el pago con Stripe.
     *
     * @param token El token de autorización del usuario.
     * @param currency La moneda de la transacción (ej: "usd").
     * @param tipo El tipo de vivienda.
     * @param pais El país.
     * @param municipio El municipio.
     * @param estado El estado.
     * @param calle La calle.
     * @param noExterior El número exterior.
     * @param colonia La colonia.
     * @param cp El código postal.
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

    /**
     * Actualiza el estado de la dirección de entrega.
     *
     * @param tipo El tipo de vivienda.
     * @param pais El país.
     * @param municipio El municipio.
     * @param estado El estado.
     * @param calle La calle.
     * @param noExterior El número exterior.
     * @param noInterior El número interior.
     * @param colonia La colonia.
     * @param cp El código postal.
     */
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
