package com.larc.appandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.larc.appandroid.model.NetworkManager

/**
 * Representa el manejo de la conexión a internet.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */
class NetworkManagerVM(application: Application) : AndroidViewModel(application) {

    private val networkManager = NetworkManager(application)

    /**
     * Verifica si hay conexión a internet.
     *
     * @return `true` si hay conexión, `false` en caso contrario.
     */
    fun checkNetworkConnection(): Boolean {
        return networkManager.isNetworkAvailable()
    }
}
