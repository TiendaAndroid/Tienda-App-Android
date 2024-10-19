package com.larc.appandroid.viewmodel

import android.util.Log
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
    private val _messageShowed = MutableStateFlow(false)
    val messageShowed: MutableStateFlow<Boolean> = _messageShowed
    private val _carritoNoRepeat = MutableStateFlow(listOf<CartItemQ>())
    val carritoNoRepeat: MutableStateFlow<List<CartItemQ>> = _carritoNoRepeat
    private val _subtotalCarrito = MutableStateFlow(0.0)
    val subtotalCarrito: MutableStateFlow<Double> = _subtotalCarrito
    private val _totalCarrito = MutableStateFlow(0.0)
    val totalCarrito: MutableStateFlow<Double> = _totalCarrito

    //-------------------------------------------------------------------------------------
    // Interface para la vista

    /**
     * Agrega un producto al carrito.
     *
     * Envía una solicitud para agregar un producto al carrito del usuario. Si la operación es exitosa,
     * actualiza el estado del carrito y recalcula los totales. En caso de error, marca el estado de error.
     *
     * @param id El ID del producto que se va a agregar.
     * @param producto El nombre del producto que se va a agregar.
     */
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
                calculateCartTotals()
            } else {
                _errorAgregarProducto.value = true
                _productoAgregado.value = false
            }
        }
    }

    /**
     * Indica que el mensaje de éxito ya ha sido mostrado al usuario.
     */
    fun setMessageShowed() {
        _messageShowed.value = true
    }

    /**
     * Restablece los estados de error y de éxito relacionados con la adición de productos al carrito.
     */
    fun resetErrores() {
        _errorAgregarProducto.value = false
        _productoAgregado.value = false
    }

    /**
     * Consulta el carrito del usuario.
     *
     * Obtiene el contenido del carrito asociado al ID del usuario. Los productos del carrito se agrupan por su ID
     * y se calculan las cantidades totales por producto. Luego, se actualiza el estado del carrito y se recalculan los totales.
     *
     * @param id El ID del usuario cuyo carrito se va a consultar.
     */
    fun getCart(id: String) {
        _errorGetCart.value = false
        viewModelScope.launch {
            val result = servicioRemotoDireccion.getCart(id)
            if (result != null) {
                _productosCarrito.value = result.cartItems
                val groupedItems = result.cartItems
                    .groupBy { it.product.id }
                    .map { (productId, items) ->
                        val totalQuantity = items.sumOf { it.quantity }
                        CartItemQ(
                            productId = productId,
                            quantity = totalQuantity,
                            name = items[0].product.name,
                            price = items[0].product.price,
                            image = items[0].product.image[0].url
                        )
                    }
                _carritoNoRepeat.value = groupedItems
                _errorGetCart.value = false
                calculateCartTotals()
            } else {
                _productosCarrito.value = listOf()
                _carritoNoRepeat.value = listOf()
                _errorGetCart.value = true
            }
        }
    }

    /**
     * Calcula los totales del carrito.
     *
     * Calcula el subtotal sumando el precio multiplicado por la cantidad de cada producto en el carrito, y luego calcula
     * el total aplicando un impuesto del 16%.
     */
    private fun calculateCartTotals() {
        val subtotal = _carritoNoRepeat.value.sumOf { it.price * it.quantity }
        _subtotalCarrito.value = subtotal
        _totalCarrito.value = subtotal * 1.16
    }

    /**
     * Elimina un producto del carrito.
     *
     * Busca el producto en el carrito usando su ID de producto, obtiene el ID del elemento del carrito (`cartItem`) y lo elimina.
     * Luego, actualiza el estado del carrito y recalcula los totales.
     *
     * @param productId El ID del producto que se desea eliminar del carrito.
     */
    fun removeFromCart(productId: String) {
        val cartItem = _productosCarrito.value.find { it.product.id == productId }

        cartItem?.let {
            val cartItemId = it.id
            viewModelScope.launch {
                val result = servicioRemotoDireccion.deleteItem(cartItemId)
                if (result != null) {
                    _productosCarrito.value = _productosCarrito.value.filterNot { it.id == cartItemId }
                    refreshCart()
                } else {
                    Log.d("Status", "Failed to remove item")
                }
            }
        } ?: run {
            Log.d("Status", "CartItem not found for productId: $productId")
        }
    }

    /**
     * Actualiza el estado del carrito.
     *
     * Agrupa los productos del carrito por su ID, calcula las cantidades totales por producto, y recalcula los totales del carrito.
     */
    private fun refreshCart() {
        val groupedItems = _productosCarrito.value
            .groupBy { it.product.id }
            .map { (productId, items) ->
                val totalQuantity = items.sumOf { it.quantity }
                CartItemQ(
                    productId = productId,
                    quantity = totalQuantity,
                    name = items[0].product.name,
                    price = items[0].product.price,
                    image = items[0].product.image[0].url
                )
            }
        _carritoNoRepeat.value = groupedItems
        calculateCartTotals()
    }

}
