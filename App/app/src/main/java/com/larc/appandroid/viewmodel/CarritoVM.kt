package com.larc.appandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larc.appandroid.model.AddToCartRequest
import com.larc.appandroid.model.CartItem
import com.larc.appandroid.model.ServicioRemotoCarrito
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * Representa el viewmodel relacionado con el carrito.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

class CarritoVM: ViewModel() {

    // Modelo
    private val servicioRemotoDireccion = ServicioRemotoCarrito()

    //-------------------------------------------------------------------------------------
    // Estado
    private val _errorAgregarProducto = MutableStateFlow(false)
    val errorAgregarProducto: MutableStateFlow<Boolean> = _errorAgregarProducto
    private val _productoAgregado = MutableStateFlow(false)
    val productoAgregado: MutableStateFlow<Boolean> = _productoAgregado
    private val _productosCarrito = MutableStateFlow(listOf<CartItem>())
    val productosCarrito: MutableStateFlow<List<CartItem>> = _productosCarrito
    private val _errorGetCart = MutableStateFlow(false)
    val errorGetCart: MutableStateFlow<Boolean> = _errorGetCart
    private val _messageShowed = MutableStateFlow(false)
    val messageShowed: MutableStateFlow<Boolean> = _messageShowed

    //-------------------------------------------------------------------------------------
    // Interface para la vista
    fun addToCart(id: String, producto: String) {
        _messageShowed.value = false
        _errorAgregarProducto.value = false
        _productoAgregado.value = false
        val addToCartRequest = AddToCartRequest(id, producto, 1)
        viewModelScope.launch {
            val result = servicioRemotoDireccion.addToCart(addToCartRequest)
            if (result != null) {
                _errorAgregarProducto.value = false
                _productoAgregado.value = true
            } else {
                _errorAgregarProducto.value = true
                _productoAgregado.value = false
            }
        }
    }
    fun setMessageShowed() {
        _messageShowed.value = true
    }
    fun resetErrores() {
        _errorAgregarProducto.value = false
        _productoAgregado.value = false
    }
    fun getCart(id: String) {
        _errorGetCart.value = false
        viewModelScope.launch {
            val result = servicioRemotoDireccion.getCart(id)
            if (result != null) {
                _productosCarrito.value = result.cartItems
                _errorGetCart.value = false
            } else {
                _productosCarrito.value = listOf()
                _errorGetCart.value = true
            }
        }
    }
    fun resetErrorGetCart() {
        _errorGetCart.value = false
    }

}