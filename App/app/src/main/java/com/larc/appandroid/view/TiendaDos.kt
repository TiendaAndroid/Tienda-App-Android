package com.larc.appandroid.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larc.appandroid.R

@Composable
fun TiendaDos(modifier: Modifier = Modifier) {
    Column(modifier = Modifier.fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text="Explorando:",
            fontWeight = FontWeight.Normal,
            fontSize = 26.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text="Categoría seleccionada",
            fontWeight = FontWeight.Normal,
            fontSize = 20.sp,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier
            .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                TarjetaProducto(text = "Producto 1", painterResource(id = R.drawable.sampletoalla1), onClick = {})
                TarjetaProducto(text = "Producto 2", painterResource(id = R.drawable.sampletoalla2), onClick = {})
            }
        }
    }
}

@Composable
fun TarjetaProducto(text: String, image: Painter, onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize(.95f)
            .clip(RoundedCornerShape(20.dp))
            .border(2.dp, Color.LightGray, RoundedCornerShape(20.dp))
    ) {
        Row {
            Image(
                painter = image,
                contentDescription = "Logo de Todas Brillamos",
                modifier = Modifier
                    .weight(1f)
                    //.fillMaxWidth()
                    //.height(150.dp)
                    .padding(10.dp)
                    .clip(RoundedCornerShape(13.dp))
            )
            Text(
                text = text,
                fontSize = 17.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TiendaDosPreview() {
    TiendaDos()
}
