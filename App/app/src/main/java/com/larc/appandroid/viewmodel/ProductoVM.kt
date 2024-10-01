package com.larc.appandroid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larc.appandroid.model.Producto
import com.larc.appandroid.model.ServicioRemotoProducto
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.math.ceil

/**
 * Representa el viewmodel relacionado con los productos.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

class ProductoVM: ViewModel() {
    // Modelo
    private val servicioRemotoProducto = ServicioRemotoProducto()

    //-------------------------------------------------------------------------------------
    // Estado
    // Lista de productos
    private val _listaTodosProductos = MutableStateFlow(listOf<Producto>())
    val estadoListaTodosProductos: StateFlow<List<Producto>> = _listaTodosProductos
    // Página actual
    private val _paginaActual = MutableStateFlow(0)
    val estadoPaginaActual: StateFlow<Int> = _paginaActual
    // Total de páginas
    private val _totalPaginas = MutableStateFlow(0)
    val estadoTotalPaginas: StateFlow<Int> = _totalPaginas
    // Regresar hasta arriba de la lista
    private val _scrollTop = MutableStateFlow(false)
    val estadoScrollTop: StateFlow<Boolean> = _scrollTop
    // Total de resultados al buscar por palabra
    private val _totalResultados = MutableStateFlow(0)
    val estadoTotalResultados: StateFlow<Int> = _totalResultados

    private var filtered = false
    private var currentCat = ""

    private var otherOffset = 0

    // Lista sin resultados
    private val _sinResultados = MutableStateFlow(false)
    val estadoSinResultados: StateFlow<Boolean> = _sinResultados
    // Sin resultados en detalle producto
    private val _sinResultIndiv = MutableStateFlow(false)
    val estadoSinResultIndiv: StateFlow<Boolean> = _sinResultIndiv
    // Lista de búsqueda
    private val _listaBusqueda = MutableStateFlow(listOf<Producto>())
    val estadoListaBusqueda: StateFlow<List<Producto>> = _listaBusqueda

    // Estado del producto actual (detalle producto)
    private val _estadoProductoActual = MutableStateFlow<Producto?>(null)
    val estadoProductoActual: StateFlow<Producto?> = _estadoProductoActual

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    //-------------------------------------------------------------------------------------
    // Interface para la vista
    // Calcula el número de páginas de resultados
    private fun calcularPaginas(total: Int): Int {
        val num = ceil(total.toDouble() / 5.0)
        return num.toInt()
    }

    // Para todos los productos
    fun getAllProductos(offset: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = servicioRemotoProducto.getProductos(offset)
            if (result != null) {
                val products = result.data
                Log.d("ProductoVM", "Products fetched: ${products.size}")
                _listaTodosProductos.value = products
                _totalPaginas.value = calcularPaginas(result.totalResults)
                _sinResultados.value = false
            } else {
                Log.d("ProductoVM", "Error fetching products")
                _sinResultados.value = true
            }
            _isLoading.value = false
        }
    }

    // Para productos por categoría
    fun getProductosByCategory(cat: String, offset: Int) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = servicioRemotoProducto.getProductosPorCategoria(cat, offset)
            if (result != null) {
                val products = result.data
                Log.d("ProductoVM", "Products fetched: ${products.size}")
                _listaTodosProductos.value = products
                _totalPaginas.value = calcularPaginas(result.totalResults)
                setCategory(cat)
                _sinResultados.value = false
            } else {
                Log.d("ProductoVM", "Error fetching products")
                _sinResultados.value = true
            }
            _isLoading.value = false
        }
    }

    // Para un producto por ID
    fun getProductoPorId(id: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = servicioRemotoProducto.getProductoPorId(id)
            if (result != null) {
                Log.d("ProductoVM", "Product fetched: ${result.name}")
                _estadoProductoActual.value = result
                _sinResultIndiv.value = false
            } else {
                Log.d("ProductoVM", "Error fetching product")
                _sinResultIndiv.value = true
            }
            _isLoading.value = false
        }
    }

    // Para productos por búsqueda
    fun searchProducto(palabra: String) {
        _isLoading.value = true
        viewModelScope.launch {
            val result = servicioRemotoProducto.searchProducts(palabra)
            if (result != null) {
                val products = result.data
                Log.d("ProductoVM", "Products fetched: ${products.size}")
                _listaBusqueda.value = products
                _totalResultados.value = products.size
                _sinResultados.value = false
            } else {
                Log.d("ProductoVM", "Error fetching products")
                _sinResultados.value = true
            }
            _isLoading.value = false
        }
    }

    private fun setCategory(cat: String) {
        currentCat = cat
    }
    // Filtrar o no
    fun filterOn() {
        filtered = true
    }
    fun filterOff() {
        filtered = false
    }
    fun nextPage() {
        if (_paginaActual.value < _totalPaginas.value-1) {
            _paginaActual.value++
            otherOffset += 1
            if (filtered) {
                getProductosByCategory(currentCat, otherOffset)
            } else {
                getAllProductos(otherOffset)
            }
            _scrollTop.value = true
        }
    }
    fun previousPage() {
        if (_paginaActual.value > 0) {
            _paginaActual.value--
            otherOffset -= 1
            if (filtered) {
                getProductosByCategory(currentCat, otherOffset)
            } else {
                getAllProductos(otherOffset)
            }
            _scrollTop.value = true
        }
    }
    fun resetPagActual() {
        _paginaActual.value = 0
        otherOffset = 0
    }
    fun resetScrollTop() {
        _scrollTop.value = false
    }
}