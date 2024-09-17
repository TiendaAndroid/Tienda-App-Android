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

    private var beginning = true
    private var ending = false

    // Interface para la vista
    fun getAllProductos() {
        viewModelScope.launch {
            _listaTodosProductos.value = servicioRemotoProducto.getProductos()
        }
    }
}