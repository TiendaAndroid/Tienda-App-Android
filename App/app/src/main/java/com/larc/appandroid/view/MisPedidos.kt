package com.larc.appandroid.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier

@Composable
fun MisPedidos() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Pantalla de Mis Pedidos")
    }
}

@Preview(showBackground = true)
@Composable
fun MisPedidosPreview() {
    MisPedidos()
}
