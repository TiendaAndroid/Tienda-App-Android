package com.larc.appandroid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larc.appandroid.model.CartItem
import com.larc.appandroid.model.Order
import com.larc.appandroid.model.ServicioRemotoOrders
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class OrdenVM: ViewModel() {

    // Modelo
    private val servicioRemotoDireccion = ServicioRemotoOrders()

    //-------------------------------------------------------------------------------------
    // Estado
    private val _estadoThisOrder = MutableStateFlow( EstadoOrderDos() )
    val estadoThisOrder: StateFlow<EstadoOrderDos> = _estadoThisOrder

    //-------------------------------------------------------------------------------------
    // Interface para la vista
    fun getOrders(id: String) {
        viewModelScope.launch {
            val result = servicioRemotoDireccion.getOrder(id)
            if (result != null) {
                _estadoThisOrder.value = result.body()?.let {
                    _estadoThisOrder.value.copy(
                        id = it.id,
                        status = it.status,
                        pais = it.pais,
                        municipio = it.municipio,
                        estado = it.estado,
                        calle = it.calle,
                        noExterior = it.noExterior,
                        noInterior = it.noInterior,
                        colonia = it.colonia,
                        cp = it.cp,
                        createdAt = it.createdAt,
                        orderItems = it.orderItems,
                    )
                }!!
                Log.d("OrdenVM", _estadoThisOrder.value.orderItems.toString())
                Log.d("OrdenVM", _estadoThisOrder.value.id)
            } else {
                Log.d("OrdenVM", "Error fetching orders")
                _estadoThisOrder.value = EstadoOrderDos()
            }
        }
    }

}