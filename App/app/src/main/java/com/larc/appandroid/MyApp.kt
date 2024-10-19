package com.larc.appandroid

import android.app.Application
import com.stripe.android.PaymentConfiguration

/**
 * Representa la aplicación, es útil para la persistencia del token en la app y para inicializar la configuración de Stripe.
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

        PaymentConfiguration.init(
            applicationContext,
            // Llave publicable de Stripe
            "pk_test_51PnVUTCyP15WNurR7ZHcJe0ynuwOnGuMd18u5jLCvfT4r57p9tcI61nPiOC1Ao2grYNCLENTCQ8CERM0X8Fd5Tou00E32PFM2y"
        )
    }
}
