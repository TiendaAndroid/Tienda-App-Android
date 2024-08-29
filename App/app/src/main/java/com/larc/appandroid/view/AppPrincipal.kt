package com.larc.appandroid.view

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.larc.appandroid.ui.theme.AppAndroidTheme

@Composable
fun AppPrincipal() {
    val navController = rememberNavController()

    // Define state for search text
    var searchText by remember { mutableStateOf("") }

    // Define a handler for menu click
    val onMenuClick = {
        // Handle menu click here, e.g., show a menu or navigate somewhere
    }

    // Define a handler for text change
    val onSearchTextChanged: (String) -> Unit = { text ->
        searchText = text
    }
    AppAndroidTheme {
        Scaffold(
            /*modifier = Modifier.fillMaxSize()) { innerPadding ->
            MenuPrincipal(modifier = Modifier.padding(innerPadding))
            */
            topBar = { AppTopBar(
                onMenuClick = onMenuClick,
                onSearchTextChanged = onSearchTextChanged,
                searchText = searchText
            ) },
            bottomBar = { AppBottomBar(navController) },
        ) { innerPadding ->
            AppNavHost(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    onMenuClick: () -> Unit,
    onSearchTextChanged: (String) -> Unit,
    searchText: String
) {
    TopAppBar(
        title = {
            TextField(
                value = searchText,
                onValueChange = onSearchTextChanged,
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                placeholder = {
                    Text("Busca un producto")
                },
                singleLine = true,
                shape = RoundedCornerShape(30.dp),
                /*colors = OutlinedTextFieldDefaults.colors(
//                    focusedContainerColor = Color.Transparent, // Remove focused background
//                    unfocusedContainerColor = Color.Transparent, // Remove unfocused background
                    focusedBorderColor = Color.Transparent, // Remove focused outline
                    unfocusedBorderColor = Color.Transparent // Remove unfocused outline
                ),*/
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        },
        actions = {
            IconButton(
                onClick = onMenuClick
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
        },
        modifier = Modifier.padding(top = 16.dp)
    )
}


@Composable
fun AppBottomBar(navController: NavHostController) {
    BottomAppBar {
        val pilaNavegacion by navController.currentBackStackEntryAsState()
        val pantallaActual = pilaNavegacion?.destination
        Pantallas.listaPantallas.forEach { pantalla ->
            NavigationBarItem(
                selected = pantalla.ruta == pantallaActual?.route,
                onClick = {
                    navController.navigate(pantalla.ruta) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text(text = pantalla.etiqueta) },
                icon = { Icon(imageVector = pantalla.icono,
                    contentDescription = pantalla.etiqueta) },
                alwaysShowLabel = true
            )
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Pantallas.RUTA_HOME,
        modifier = modifier.fillMaxSize()
    ) {
        composable(Pantallas.RUTA_HOME) {
            Home()
        }
        composable(Pantallas.RUTA_PERFIL) {
            Perfil()
        }
        composable(Pantallas.RUTA_CARRITO) {
            Carrito()
        }
    }
}
