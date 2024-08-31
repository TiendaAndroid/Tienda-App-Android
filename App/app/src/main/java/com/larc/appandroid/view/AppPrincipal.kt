package com.larc.appandroid.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
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
    var searchText by remember { mutableStateOf("") }

    val onSearchTextChanged: (String) -> Unit = { text ->
        searchText = text
    }

    AppAndroidTheme {
        Scaffold(
            topBar = {
                AppTopBar(
                    navController = navController, // Pasar el NavController aquí
                    onSearchTextChanged = onSearchTextChanged,
                    searchText = searchText
                )
            },
            bottomBar = { AppBottomBar(navController) },
        ) { innerPadding ->
            AppNavHost(navController, modifier = Modifier.padding(innerPadding))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    navController: NavHostController, // Recibir el NavController como parámetro
    onSearchTextChanged: (String) -> Unit,
    searchText: String
) {
    var expanded by remember { mutableStateOf(false) } // Controla el estado del menú desplegable

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
                shape = RoundedCornerShape(40.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            )
        },
        actions = {
            IconButton(
                onClick = { expanded = true } // Cambia el estado para mostrar el menú
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false } // Cierra el menú al hacer clic fuera
            ) {
                DropdownMenuItem(
                    text = { Text("Mis pedidos") },
                    onClick = {
                        expanded = false
                        navController.navigate(Pantallas.RUTA_MIS_PEDIDOS) // Navegar a Mis Pedidos
                    }
                )
                DropdownMenuItem(
                    text = { Text("Mi información") },
                    onClick = {
                        expanded = false
                        navController.navigate(Pantallas.RUTA_PERFIL) // Navegar a Perfil
                    }
                )
                DropdownMenuItem(
                    text = { Text("Direcciones") },
                    onClick = {
                        expanded = false
                        // Navegar a la pantalla de Direcciones, si la tienes
                    }
                )

                DropdownMenuItem(
                    text = { Text("Servicio al cliente") },
                    onClick = {
                        expanded = false
                        // Navegar a la pantalla de Servicio al Cliente, si la tienes
                    }
                )

                DropdownMenuItem(
                    text = { Text("Pagos") },
                    onClick = {
                        expanded = false
                        // Navegar a la pantalla de Pagos, si la tienes
                    }
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
                    // Usa navigate con una opción de popUpTo para evitar pilas de navegación innecesarias
                    navController.navigate(pantalla.ruta) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                label = { Text(text = pantalla.etiqueta) },
                icon = {
                    Icon(
                        imageVector = pantalla.icono,
                        contentDescription = pantalla.etiqueta
                    )
                },
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
        // Agregar esta línea para la ruta de Mis Pedidos
        composable(Pantallas.RUTA_MIS_PEDIDOS) {
            MisPedidos()
        }
    }
}


