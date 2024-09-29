package com.larc.appandroid.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EliminarDireccion(modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFAF8FF)),
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "¿Deseas eliminar esta dirección?",
                textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                color = AppColors.RosaZazil,
                fontWeight = FontWeight.Normal,
                fontSize = 22.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "La dirección",
                color = AppColors.GrisOscuro,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                //modifier = Modifier.fillMaxWidth()
            )
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {},
                    border = BorderStroke(2.dp, Color.LightGray),
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth(.9f)
                        .clip(RoundedCornerShape(20.dp))
                        .padding(16.dp),
                    shape = RoundedCornerShape(20),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.White,
                        contentColor = AppColors.GrisOscuro
                    )
                ) {
                    Row {
                        Text(
                            text = "Eliminar dirección",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Añadir dirección",
                            tint = AppColors.RosaZazil
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = {},
                    border = BorderStroke(2.dp, Color.LightGray),
                    modifier = Modifier
                        .height(80.dp)
                        .fillMaxWidth(.9f)
                        .clip(RoundedCornerShape(20.dp))
                        .padding(16.dp),
                    shape = RoundedCornerShape(20),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = AppColors.White,
                        contentColor = AppColors.GrisOscuro
                    )
                ) {
                    Row {
                        Text(
                            text = "Cancelar",
                            fontSize = 17.sp,
                            fontWeight = FontWeight.Normal
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Icon(
                            imageVector = Icons.Default.Done,
                            contentDescription = "Añadir dirección",
                            tint = AppColors.RosaZazil
                        )
                    }
                }
            }
        }
        item {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}
