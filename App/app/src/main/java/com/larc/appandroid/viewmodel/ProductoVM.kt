package com.larc.appandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larc.appandroid.model.Producto
import com.larc.appandroid.model.ServicioRemotoProducto
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