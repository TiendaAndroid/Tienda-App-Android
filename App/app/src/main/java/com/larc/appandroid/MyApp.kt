package com.larc.appandroid

import android.app.Application

/**
 * Representa la aplicación, es útil para la persistencia del token en la app.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

class MyApp : Application() {
    companion object {
        lateinit var instance: MyApp
            private set
    }
    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}
