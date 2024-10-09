package com.larc.appandroid.viewmodel

import androidx.lifecycle.ViewModel
import com.larc.appandroid.model.CartItem
import com.larc.appandroid.model.Order
import com.larc.appandroid.model.ServicioRemotoOrders
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class OrdenVM: ViewModel() {

    // Modelo
    private val servicioRemotoDireccion = ServicioRemotoOrders()

    //-------------------------------------------------------------------------------------
    // Estado
    private val _estadoThisOrder = MutableStateFlow( EstadoOrderDos() )
    val estadoThisOrder: StateFlow<EstadoOrderDos> = _estadoThisOrder

    //-------------------------------------------------------------------------------------
    // Interface para la vista
}