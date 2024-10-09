package com.larc.appandroid.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.larc.appandroid.model.NetworkManager

class NetworkManagerVM(application: Application) : AndroidViewModel(application) {

    private val networkManager = NetworkManager(application)

    fun checkNetworkConnection(): Boolean {
        return networkManager.isNetworkAvailable()
    }
}
