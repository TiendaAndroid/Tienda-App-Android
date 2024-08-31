package com.larc.appandroid.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Faqs(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier.fillMaxSize(), // Make the Box fill the entire screen
        contentAlignment = Alignment.Center // Center content horizontally and vertically
    ) {
        Text(text = "Preguntas frecuentes")
    }
}