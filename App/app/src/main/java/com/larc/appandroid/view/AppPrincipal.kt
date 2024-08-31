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
                    navController = navController,
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
    navController: NavHostController,
    onSearchTextChanged: (String) -> Unit,
    searchText: String
) {
    var expanded by remember { mutableStateOf(false) }

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
                onClick = { expanded = true }
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu"
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Acerca de") },
                    onClick = {
                        expanded = false
                        navigateTo(navController, Pantallas.RUTA_ACERCAC_DE)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Tienda") },
                    onClick = {
                        expanded = false
                        navigateTo(navController, Pantallas.RUTA_TIENDA_UNO)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Testimonios") },
                    onClick = {
                        expanded = false
                        navigateTo(navController, Pantallas.RUTA_TESTIMONIOS)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Preguntas frecuentes") },
                    onClick = {
                        expanded = false
                        navigateTo(navController, Pantallas.RUTA_FAQS)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Servicio al cliente") },
                    onClick = {
                        expanded = false
                        navigateTo(navController, Pantallas.RUTA_SERVICIO_CLIENTE)
                    }
                )
            }
        },
        modifier = Modifier.padding(top = 16.dp)
    )
}

fun navigateTo(navController: NavHostController, ruta: String) {
    navController.navigate(ruta) {
        popUpTo(navController.graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
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
        composable(Pantallas.RUTA_CUENTA) {
            Cuenta(navigateTo = { route ->
                navController.navigate(route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            })
        }
        composable(Pantallas.RUTA_CARRITO) {
            Carrito()
        }
        composable(Pantallas.RUTA_MIS_PEDIDOS) {
            MisPedidos()
        }
        composable(Pantallas.RUTA_FAQS) {
            Faqs()
        }
        composable(Pantallas.RUTA_ACERCAC_DE) {
            AcercaDe()
        }
        composable(Pantallas.RUTA_SERVICIO_CLIENTE) {
            ServicioCliente()
        }
        composable(Pantallas.RUTA_TESTIMONIOS) {
            Testimonios()
        }
        composable(Pantallas.RUTA_TIENDA_UNO) {
            TiendaUno()
        }
        composable(Pantallas.RUTA_MI_INFORMACION) {
            MiInformacion()
        }
        composable(Pantallas.RUTA_DIRECCIONES) {
            Direcciones()
        }
    }
}


