package com.larc.appandroid.model

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities

/**
 * Esta clase gestiona la verificación de la conectividad a internet en el dispositivo.
 * Proporciona un método para determinar si hay una conexión activa, ya sea a través de WiFi o de datos móviles.
 *
 * @param context El contexto necesario para acceder a los servicios del sistema.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
class NetworkManager(private val context: Context) {

    /**
     * Verifica si hay una conexión de red disponible.
     *
     * @return true si hay conexión a WiFi o datos móviles, de lo contrario false.
     */
    fun isNetworkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork) ?: return false
        return when {
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> false
        }
    }
}
