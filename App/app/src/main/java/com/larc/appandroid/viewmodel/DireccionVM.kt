package com.larc.appandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larc.appandroid.model.DireccionCompletaRequest
import com.larc.appandroid.model.ServicioRemotoDireccion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class DireccionVM: ViewModel() {

    // Modelo
    private val servicioRemotoDireccion = ServicioRemotoDireccion()

    //-------------------------------------------------------------------------------------
    // Estado
    private val _errorNuevaDireccion = MutableStateFlow(false)
    val errorNuevaDireccion: MutableStateFlow<Boolean> = _errorNuevaDireccion
    private val _direccionCreada = MutableStateFlow(false)
    val direccionCreada: MutableStateFlow<Boolean> = _direccionCreada
    private val _errorEliminarDireccion = MutableStateFlow(false)
    val errorEliminarDireccion: MutableStateFlow<Boolean> = _errorEliminarDireccion
    private val _direccionEliminada = MutableStateFlow(false)
    val direccionEliminada: MutableStateFlow<Boolean> = _direccionEliminada

    //-------------------------------------------------------------------------------------
    // Interface para la vista
    fun createAddress(token: String,
                      tipo: String,
                      pais: String,
                      municipio: String,
                      estado: String,
                      calle: String,
                      noExterior: String,
                      noInterior: String?,
                      colonia: String,
                      cp: Int) {
        _errorNuevaDireccion.value = false
        _direccionCreada.value = false
        if (token!="" && tipo!="" && pais!="" && municipio!="" && estado!="" && calle!="" && noExterior!="" && colonia!="" && cp!=0) {
            val direccionCompletaRequest = DireccionCompletaRequest(
                tipo, pais, municipio, estado, calle, noExterior, noInterior, colonia, cp
            )
            viewModelScope.launch {
                val result = servicioRemotoDireccion.createAddress(token, direccionCompletaRequest)
                if (result != null) {
                    _errorNuevaDireccion.value = false
                    _direccionCreada.value = true
                } else {
                    _errorNuevaDireccion.value = true
                    _direccionCreada.value = false
                }
            }
        } else {
            _errorNuevaDireccion.value = true
            _direccionCreada.value = false
        }
    }
    fun resetErrores() {
        _errorNuevaDireccion.value = false
        _direccionCreada.value = false
    }
    fun deleteAddress(token: String, id: String) {
        viewModelScope.launch {
            val result = servicioRemotoDireccion.deleteAddress(token, id)
            if (result != null) {
                _errorEliminarDireccion.value = false
                _direccionEliminada.value = true
            } else {
                _errorEliminarDireccion.value = true
                _direccionEliminada.value = false
            }
        }
    }
}