package com.larc.appandroid.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larc.appandroid.model.ServicioRemotoOrders
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * Representa el viewmodel relacionado con las ordenes.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
class OrdenVM: ViewModel() {

    // Modelo
    private val servicioRemotoDireccion = ServicioRemotoOrders()

    //-------------------------------------------------------------------------------------
    // Estado
    private val _estadoThisOrder = MutableStateFlow( EstadoOrderDos() )
    val estadoThisOrder: StateFlow<EstadoOrderDos> = _estadoThisOrder
    private val _shortList = MutableStateFlow(listOf<NameQuant>())
    val shortList: StateFlow<List<NameQuant>> = _shortList

    //-------------------------------------------------------------------------------------
    // Interface para la vista

    /**
     * Obtiene las ordenes de un usuario.
     *
     * @param id El ID del usuario.
     */
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
                groupOrderItemsByName()
                Log.d("OrdenVM", _estadoThisOrder.value.orderItems.toString())
                Log.d("OrdenVM", _estadoThisOrder.value.id)
            } else {
                Log.d("OrdenVM", "Error fetching orders")
                _estadoThisOrder.value = EstadoOrderDos()
            }
        }
    }

    /**
     * Obtiene el detalle de una orden.
     */
    private fun groupOrderItemsByName() {
        val groupedItems = _estadoThisOrder.value.orderItems
            ?.groupBy { it.product.name }
            ?.mapValues { entry ->
                entry.value.sumOf { it.quantity }
            }
        val nameQuantList = groupedItems?.map { (name, quantity) ->
            NameQuant(name = name, quantity = quantity)
        }
        if (nameQuantList != null) {
            _shortList.value = nameQuantList
        }
    }

}
