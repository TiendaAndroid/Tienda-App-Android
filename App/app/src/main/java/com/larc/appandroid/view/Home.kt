package com.larc.appandroid.view

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.larc.appandroid.model.NetworkManager
import com.larc.appandroid.viewmodel.NetworkManagerVM

/**
 * Representa la vista de inicio de la aplicación.
 * PENDIENTE: HACER TODDO LO QUE LLEVA
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@Composable
fun Home(modifier: Modifier = Modifier) {

    Box(
        modifier = Modifier.fillMaxSize()
            .background(Color(0xFFFAF8FF)),
        contentAlignment = Alignment.Center

    ) {
        Text(text = "Home")
    }

}