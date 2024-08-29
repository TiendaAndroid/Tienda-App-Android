package com.larc.appandroid.view

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.larc.appandroid.ui.theme.AppAndroidTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPrincipal(modifier: Modifier = Modifier) {
    Column {
        TopAppBar(
            title = { Text("") },
            navigationIcon = {
                IconButton(
                    onClick = { },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = "Menú"
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = { },
                    modifier = Modifier.padding(top = 16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Carrito"
                    )
                }
            }
        )
        LazyColumn(
            modifier = modifier
                .padding(16.dp)
                .weight(1f)
        ) {
            items(50) { index ->
                Text(
                    text = "Elemento $index",
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}

@Preview(showBackground = true, widthDp = 450, heightDp = 3000)
@Composable
fun MenuPrincipalPreview() {
    AppAndroidTheme {
        MenuPrincipal()
    }
}
