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

    private val _stock = MutableStateFlow(0)
    val stock: StateFlow<Int> = _stock

    private val _listHome = MutableStateFlow(listOf<HomeProduct>())
    private val _currentHome = MutableStateFlow<HomeProduct?>(null)
    val currentHome: StateFlow<HomeProduct?> = _currentHome
    private val _currentHomeIndex = MutableStateFlow(0)
    val currentHomeIndex: StateFlow<Int> = _currentHomeIndex
    private val _totalHomeItems = MutableStateFlow(0)
    val totalHomeItems: StateFlow<Int> = _totalHomeItems

    //-------------------------------------------------------------------------------------
    // Interface para la vista

    /**
     * Calcula el número de páginas basado en el total de resultados.
     *
     * Dado un número total de productos, calcula cuántas páginas de 5 productos por página serían necesarias.
     *
     * @param total El número total de productos.
     * @return El número de páginas calculado.
     */
    private fun calcularPaginas(total: Int): Int {
        val num = ceil(total.toDouble() / 5.0)
        return num.toInt()
    }

    /**
     * Obtiene todos los productos con paginación.
     *
     * Envía una solicitud para obtener productos, utilizando el `offset` para la paginación.
     * Actualiza la lista de productos y el número de páginas totales.
     *
     * @param offset El número de productos a saltar (usado para la paginación).
     */
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

    /**
     * Obtiene productos por categoría con paginación.
     *
     * Envía una solicitud para obtener productos filtrados por categoría, utilizando el `offset` para la paginación.
     * Actualiza la lista de productos, el número de páginas totales y la categoría actual.
     *
     * @param cat La categoría de productos.
     * @param offset El número de productos a saltar (usado para la paginación).
     */
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

    /**
     * Obtiene un producto específico por su ID.
     *
     * Envía una solicitud para obtener los detalles de un producto mediante su ID y actualiza el estado del producto actual.
     *
     * @param id El ID del producto que se desea consultar.
     */
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

    fun getStock(id: String) {
        viewModelScope.launch {
            val result = servicioRemotoProducto.getProductoPorId(id)
            if (result != null) {
                _stock.value = result.stock
            } else {
                _stock.value = 0
            }
        }
    }

    /**
     * Realiza una búsqueda de productos por palabra clave.
     *
     * Envía una solicitud para buscar productos que coincidan con una palabra clave y actualiza la lista de resultados de búsqueda.
     *
     * @param palabra La palabra clave a utilizar en la búsqueda.
     */
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

    /**
     * Establece la categoría actual para filtrar productos.
     *
     * @param cat La categoría a establecer.
     */
    private fun setCategory(cat: String) {
        currentCat = cat
    }

    /**
     * Activa el filtro por categoría.
     */
    fun filterOn() {
        filtered = true
    }

    /**
     * Desactiva el filtro por categoría.
     */
    fun filterOff() {
        filtered = false
    }

    /**
     * Avanza a la siguiente página de resultados.
     *
     * Si no se ha alcanzado la última página, incrementa el `offset` y solicita los productos de la siguiente página.
     */
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

    /**
     * Retrocede a la página anterior de resultados.
     *
     * Si no se está en la primera página, decrementa el `offset` y solicita los productos de la página anterior.
     */
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

    /**
     * Restablece la página actual a la primera página.
     */
    fun resetPagActual() {
        _paginaActual.value = 0
        otherOffset = 0
    }

    /**
     * Restablece el valor de `scrollTop` a falso.
     */
    fun resetScrollTop() {
        _scrollTop.value = false
    }

    fun getProductosHome() {
        viewModelScope.launch {
            val result = servicioRemotoProducto.getProductos(0)
            if (result != null) {
                val products = result.data
                Log.d("ProductoVM", "Products fetched: ${products.size}")

                _listHome.value = products.map {
                    HomeProduct(it.id, it.name, it.price, it.image[0].url)
                }

                _totalHomeItems.value = _listHome.value.size
                _currentHome.value = _listHome.value[_currentHomeIndex.value]
            } else {
                Log.d("ProductoVM", "Error fetching products")
            }
        }
    }

    fun nextHome() {
        if (_currentHomeIndex.value < _listHome.value.size - 1) {
            _currentHomeIndex.value++
            _currentHome.value = _listHome.value[_currentHomeIndex.value]
        } else if (_currentHomeIndex.value == _listHome.value.size - 1) {
            _currentHomeIndex.value = 0
            _currentHome.value = _listHome.value[_currentHomeIndex.value]
        }
    }

    fun prevHome() {
        if (_currentHomeIndex.value > 0) {
            _currentHomeIndex.value--
            _currentHome.value = _listHome.value[_currentHomeIndex.value]
        } else if (_currentHomeIndex.value == 0) {
            _currentHomeIndex.value = _listHome.value.size - 1
            _currentHome.value = _listHome.value[_currentHomeIndex.value]
        }
    }

}