package com.larc.appandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larc.appandroid.model.Producto
import com.larc.appandroid.model.repository.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductoVM @Inject constructor(
    private val repository: ProductRepository
) : ViewModel() {
    // State
    private val _listaTodosProductos = MutableStateFlow(listOf<Producto>())
    val estadoListaTodosProductos: StateFlow<List<Producto>> = _listaTodosProductos
    private val _paginaActual = MutableStateFlow(0)
    val estadoPaginaActual: StateFlow<Int> = _paginaActual
    private val _totalPaginas = MutableStateFlow(0)
    val estadoTotalPaginas: StateFlow<Int> = _totalPaginas

    private val _scrollTop = MutableStateFlow(false)
    val estadoScrollTop: StateFlow<Boolean> = _scrollTop
<<<<<<< HEAD
    private val _sinResultados = MutableStateFlow(false)
    val estadoSinResultados: StateFlow<Boolean> = _sinResultados

    private var filtered = false
    private var currentCat = ""
    private var otherOffset = 0

    // Fetch all products
    fun getAllProductos(offset: Int) {
        viewModelScope.launch {
            val result = repository.getAllProducts(offset)
            if (result != null) {
                val products = result.data
                Log.d("ProductoVM", "Products fetched: ${products.size}")  // Add this line to log the number of products fetched
                _listaTodosProductos.value = products
                _totalPaginas.value = result.totalResults
                _sinResultados.value = products.isEmpty()
            } else {
                Log.d("ProductoVM", "Error fetching products")  // Log error message
                _sinResultados.value = true
            }
        }
    }

    // Fetch products by category
    fun getProductosByCategory(cat: String, offset: Int) {
        viewModelScope.launch {
            val result = repository.getProductsByCategory(cat, offset)
            if (result != null) {
                val products = result.data
                _listaTodosProductos.value = products
                _totalPaginas.value = result.totalResults
                setCategory(cat)
                _sinResultados.value = products.isEmpty()
            } else {
                _sinResultados.value = true
            }
=======

    private var filtered = false
    private var currentCat = ""

    private var otherOffset = 0

    // Interface para la vista
    // Todos
    fun getAllProductos(offset: Int) {
        viewModelScope.launch {
            _listaTodosProductos.value = servicioRemotoProducto.getProductos(offset)
            getHowManyPages(offset)
        }
    }
    private fun getHowManyPages(offset: Int) {
        viewModelScope.launch {
            _totalPaginas.value = servicioRemotoProducto.getHowManyPages(offset)
        }
    }
    // Por categoría
    fun getProductosByCategory(cat: String, offset: Int) {
        viewModelScope.launch {
            _listaTodosProductos.value = servicioRemotoProducto.getProductosPorCategoria(cat, offset)
            getHowManyPagesPorCategoria(cat, offset)
            setCategory(cat)
        }
    }
    private fun getHowManyPagesPorCategoria(cat: String, offset: Int) {
        viewModelScope.launch {
            _totalPaginas.value = servicioRemotoProducto.getHowManyPagesPorCategoria(cat, offset)
>>>>>>> parent of d6fcd22 (MALDITACORRECCIONDEMIERDA)
        }
    }
    private fun setCategory(cat: String) {
        currentCat = cat
    }

    fun filterOn() {
        filtered = true
    }

    fun filterOff() {
        filtered = false
    }

    fun nextPage() {
        if (_paginaActual.value < _totalPaginas.value - 1) {
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
