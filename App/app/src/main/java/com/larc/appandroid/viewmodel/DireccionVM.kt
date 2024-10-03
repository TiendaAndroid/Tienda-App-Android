package com.larc.appandroid.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.larc.appandroid.model.DireccionCompletaRequest
import com.larc.appandroid.model.ServicioRemotoDireccion
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

/**
 * Representa el viewmodel relacionado con las direcciones.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

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
    private val _estadoMiDirecIndiv = MutableStateFlow( EstadoDirecIndiv() )
    val estadoMiDirecIndiv: MutableStateFlow<EstadoDirecIndiv> = _estadoMiDirecIndiv

    //-------------------------------------------------------------------------------------
    // Interface para la vista

    /**
     * Crea una nueva dirección para el usuario autenticado.
     *
     * Valida que todos los campos necesarios estén completos y luego envía una solicitud para crear la dirección.
     * Si la creación es exitosa, actualiza el estado para indicar que la dirección fue creada.
     *
     * @param token El token de autenticación en formato `Bearer`.
     * @param tipo El tipo de dirección (ej: "Casa", "Oficina").
     * @param pais El país de la dirección.
     * @param municipio El municipio de la dirección.
     * @param estado El estado o provincia de la dirección.
     * @param calle El nombre de la calle de la dirección.
     * @param noExterior El número exterior de la dirección.
     * @param noInterior El número interior de la dirección (opcional).
     * @param colonia La colonia o barrio de la dirección.
     * @param cp El código postal de la dirección.
     */
    fun createAddress(
        token: String,
        tipo: String,
        pais: String,
        municipio: String,
        estado: String,
        calle: String,
        noExterior: String,
        noInterior: String?,
        colonia: String,
        cp: Int
    ) {
        _errorNuevaDireccion.value = false
        _direccionCreada.value = false
        if (token != "" && tipo != "" && pais != "" && municipio != "" && estado != "" && calle != "" && noExterior != "" && colonia != "" && cp != 0) {
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

    /**
     * Restablece los errores de creación de dirección.
     */
    fun resetErrores() {
        _errorNuevaDireccion.value = false
        _direccionCreada.value = false
    }

    /**
     * Elimina una dirección por su ID.
     *
     * Envía una solicitud para eliminar una dirección específica del usuario autenticado.
     * Si la eliminación es exitosa, actualiza el estado correspondiente.
     *
     * @param token El token de autenticación en formato `Bearer`.
     * @param id El ID de la dirección que se va a eliminar.
     */
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

    /**
     * Consulta los detalles de una dirección específica.
     *
     * Envía una solicitud para obtener los detalles de una dirección individual por su ID y actualiza el estado
     * con la información obtenida.
     *
     * @param token El token de autenticación en formato `Bearer`.
     * @param id El ID de la dirección que se desea consultar.
     */
    fun getDirecIndiv(token: String, id: String) {
        viewModelScope.launch {
            val result = servicioRemotoDireccion.getAddress(token, id)
            if (result != null) {
                _estadoMiDirecIndiv.value = _estadoMiDirecIndiv.value.copy(
                    id = result.id,
                    tipo = result.tipo,
                    pais = result.pais,
                    municipio = result.municipio,
                    estado = result.estado,
                    calle = result.calle,
                    noExterior = result.noExterior,
                    noInterior = result.noInterior,
                    colonia = result.colonia,
                    cp = result.cp
                )
            }
        }
    }

    /**
     * Restablece los errores de eliminación de dirección.
     */
    fun resetErroresEliminar() {
        _errorEliminarDireccion.value = false
        _direccionEliminada.value = false
    }
}