package com.larc.appandroid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larc.appandroid.model.Imagenes
import com.larc.appandroid.model.Producto
import com.larc.appandroid.model.ServicioRemotoProducto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductoVM: ViewModel() {
    // Modelo
    private val servicioRemotoProducto = ServicioRemotoProducto()
    // Estado
    private val _listaTodosProductos = MutableStateFlow(listOf<Producto>())
    val estadoListaTodosProductos: StateFlow<List<Producto>> = _listaTodosProductos
    private val _paginaActual = MutableStateFlow(0)
    val estadoPaginaActual: StateFlow<Int> = _paginaActual
    private val _totalPaginas = MutableStateFlow(0)
    val estadoTotalPaginas: StateFlow<Int> = _totalPaginas

    private val _scrollTop = MutableStateFlow(false)
    val estadoScrollTop: StateFlow<Boolean> = _scrollTop

    private var filtered = false
    private var currentCat = ""

    private var otherOffset = 0

    private var searched = 0

    private val _searchComplete = MutableStateFlow(false)
    val searchComplete: StateFlow<Boolean> = _searchComplete

    private val _sinResultados = MutableStateFlow(false)
    val estadoSinResultados: StateFlow<Boolean> = _sinResultados

    private val _sinResultIndiv = MutableStateFlow(false)
    val estadoSinResultIndiv: StateFlow<Boolean> = _sinResultIndiv

    private val _listaBusqueda = MutableStateFlow(listOf<Producto>())
    val estadoListaBusqueda: StateFlow<List<Producto>> = _listaBusqueda

    // Estado del producto actual
    private val _estadoProductoActual = MutableStateFlow<Producto?>(null)
    val estadoProductoActual: StateFlow<Producto?> = _estadoProductoActual

    // Interface para la vista
    // Todos
    private fun getAllALLProducts() {
        viewModelScope.launch {
            val result = servicioRemotoProducto.getAllProducts()
            if (result != null) {
                val products = result.data
                Log.d("ProductoVM", "Products fetched: ${products.size}")
                _listaTodosProductos.value = products
                _sinResultados.value = false
            } else {
                Log.d("ProductoVM", "Error fetching products")
                _sinResultados.value = true
            }
        }
    }

    fun getAllProductos(offset: Int) {
        viewModelScope.launch {
            val result = servicioRemotoProducto.getProductos(offset)
            if (result != null) {
                val products = result.data
                Log.d("ProductoVM", "Products fetched: ${products.size}")
                _listaTodosProductos.value = products
                _totalPaginas.value = result.totalResults/5
                _sinResultados.value = false
            } else {
                Log.d("ProductoVM", "Error fetching products")
                _sinResultados.value = true
            }
        }
    }

    fun getProductosByCategory(cat: String, offset: Int) {
        viewModelScope.launch {
            val result = servicioRemotoProducto.getProductosPorCategoria(cat, offset)
            if (result != null) {
                val products = result.data
                Log.d("ProductoVM", "Products fetched: ${products.size}")
                _listaTodosProductos.value = products
                _totalPaginas.value = result.totalResults/5
                setCategory(cat)
                _sinResultados.value = false
            } else {
                Log.d("ProductoVM", "Error fetching products")
                _sinResultados.value = true
            }
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

    fun busquedaProducto(entrada: String) {
        if(searched<5) {
            viewModelScope.launch {
                _searchComplete.value = false
                getAllALLProducts()
                _listaBusqueda.value = _listaTodosProductos.value.filter { it.name.contains(entrada, ignoreCase = true) }
                delay(2000)
                if (_listaBusqueda.value.isEmpty()) {
                    _sinResultados.value = true
                } else {
                    _sinResultados.value = false
                }
                _searchComplete.value = true
            }
        }
        searched++
    }
    fun resetSearched() {
        searched = 0
    }

    // Para un producto por ID
    fun getProductoPorId(id: String) {
        viewModelScope.launch {
            val result = servicioRemotoProducto.getProductoPorId(id)
            if (result != null) {
                Log.d("ProductoVM", "Product fetched: ${result.name}")
                _estadoProductoActual.value = result
            } else {
                Log.d("ProductoVM", "Error fetching product")
                _sinResultIndiv.value = true
            }
        }
    }
}