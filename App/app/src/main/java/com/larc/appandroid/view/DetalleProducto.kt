package com.larc.appandroid.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.larc.appandroid.R

@Composable
fun DetalleProducto(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Toalla femenina reutilizable regular Clara",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp,
                modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                color = AppColors.RosaZazil)
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center) {
                Box(contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .padding(10.dp)
                        .height(300.dp)
                        .clip(RoundedCornerShape(13.dp))) {
                    Image(painter = painterResource(id = R.drawable.sampletoalla1), contentDescription = null)
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row(modifier = Modifier.padding(start = 20.dp, end = 20.dp)) {
                Text(text = "$",
                    fontSize = 20.sp,
                    color = AppColors.RosaZazil,
                    fontWeight = FontWeight.Bold)
                Text(text = "19.99",
                    fontSize = 20.sp,
                    color = AppColors.RosaZazil,
                    fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Características:",
                modifier = Modifier.padding(start = 20.dp, end = 20.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Fácil de limpiar y secar. Es hipoalergénica. Medida: 27x7 cm. Hecha a mano con telas de algodón.",
                modifier = Modifier.padding(start = 20.dp, end = 20.dp))
            Row(modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center) {
                BotonAgregarCarrito()
            }
        }
    }
}

@Composable
fun BotonAgregarCarrito() {
    Button(
        onClick = {  },
        contentPadding = PaddingValues(0.dp),
        modifier = Modifier
            .height(80.dp)
            .width(250.dp)
            .clip(RoundedCornerShape(20.dp))
            .padding(16.dp),
        shape = RoundedCornerShape(20),
        colors = ButtonDefaults.buttonColors(
            containerColor = AppColors.AzulZazil,
            contentColor = AppColors.White,
        ),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .fillMaxWidth(.8f)
        ) {
            Row {
                Text(
                    text = "Añadir al carrito",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 2.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Siguiente",
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDetalleProducto() {
    DetalleProducto()
}
