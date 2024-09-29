package com.larc.appandroid.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.larc.appandroid.ui.theme.AppAndroidTheme
import com.larc.appandroid.viewmodel.ProductoVM
import com.larc.appandroid.viewmodel.UsuarioVM
import kotlinx.coroutines.delay

@Composable
fun AppPrincipal() {
    val navController = rememberNavController()
    val productoVM: ProductoVM = viewModel()
    val usuarioVM: UsuarioVM = viewModel()
    var searchText by remember { mutableStateOf("") }

    val onSearchTextChanged: (String) -> Unit = { text ->
        searchText = text
    }

    usuarioVM.checkIfLoggedIn()

    AppAndroidTheme {
        Scaffold(
            topBar = {
                AppTopBar(
                    navController = navController,
                    onSearchTextChanged = onSearchTextChanged,
                    searchText = searchText,
                    usuarioVM
                )
            },
            bottomBar = { AppBottomBar(navController) },
        ) { innerPadding ->
            AppNavHost(navController, productoVM, usuarioVM, modifier = Modifier.padding(innerPadding))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    navController: NavHostController,
    onSearchTextChanged: (String) -> Unit,
    searchText: String,
    usuarioVM: UsuarioVM
) {
    var expanded by remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val loggedUsuario = usuarioVM.loggedUsuario.collectAsState()

    TopAppBar(
        modifier = Modifier.height(120.dp),
        title = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
            ) {
                TextField(
                    value = searchText,
                    onValueChange = onSearchTextChanged,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search",
                            tint = AppColors.RosaZazil
                        )
                    },
                    label = { Text("Busca un producto", color = AppColors.RosaZazil) },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(40.dp)),
                    keyboardActions = KeyboardActions(
                        onSearch = {
                            if(searchText.isNotEmpty()) {
                                navController.navigate(Pantallas.RUTA_TIENDA_TRES + "/${searchText}")
                                keyboardController?.hide()
                                onSearchTextChanged("")
                            }
                        }
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Search)
                )
            }
        },
        actions = {
            IconButton(
                onClick = { expanded = true },
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = "Menu",
                    tint = AppColors.RosaZazil,
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.background(AppColors.GrisClaro)
            ) {
                DropdownMenuItem(
                    text = { Text("Acerca de", color = AppColors.GrisOscuro) },
                    onClick = {
                        expanded = false
                        navigateTo(navController, Pantallas.RUTA_ACERCAC_DE)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Catálogo", color = AppColors.GrisOscuro) },
                    onClick = {
                        expanded = false
                        navigateTo(navController, Pantallas.RUTA_TIENDA_UNO)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Testimonios", color = AppColors.GrisOscuro) },
                    onClick = {
                        expanded = false
                        navigateTo(navController, Pantallas.RUTA_TESTIMONIOS)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Preguntas frecuentes", color = AppColors.GrisOscuro) },
                    onClick = {
                        expanded = false
                        navigateTo(navController, Pantallas.RUTA_FAQS)
                    }
                )
                DropdownMenuItem(
                    text = { Text("Servicio al cliente", color = AppColors.GrisOscuro) },
                    onClick = {
                        expanded = false
                        navigateTo(navController, Pantallas.RUTA_SERVICIO_CLIENTE)
                    }
                )
                if (!loggedUsuario.value) {
                    DropdownMenuItem(
                        text = { Text("Iniciar sesión", color = AppColors.GrisOscuro) },
                        onClick = {
                            expanded = false
                            navigateTo(navController, Pantallas.RUTA_SIGN_UP)
                        }
                    )
                }
                if (loggedUsuario.value) {
                    DropdownMenuItem(
                        text = { Text("Cerrar sesión", color = AppColors.GrisOscuro) },
                        onClick = {
                            expanded = false
                            usuarioVM.logoutUser()
                            navigateTo(navController, Pantallas.RUTA_HOME)
                        }
                    )
                }
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = AppColors.GrisClaro
        )
    )
}

fun navigateTo(navController: NavHostController, ruta: String) {
    navController.navigate(ruta) {
        if (ruta == Pantallas.RUTA_HOME) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
fun AppBottomBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = AppColors.GrisClaro,
    ) {
        val pilaNavegacion by navController.currentBackStackEntryAsState()
        val pantallaActual = pilaNavegacion?.destination

        Pantallas.listaPantallas.forEach { pantalla ->
            NavigationBarItem(
                selected = pantalla.ruta == pantallaActual?.route,
                onClick = {
                    if (pantalla.ruta == Pantallas.RUTA_HOME) {
                        navigateTo(navController, pantalla.ruta)
                    } else {
                        navController.navigate(pantalla.ruta) {
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                label = { Text(text = pantalla.etiqueta, color = AppColors.RosaZazil) },
                icon = {
                    Icon(
                        imageVector = pantalla.icono,
                        contentDescription = pantalla.etiqueta,
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = AppColors.White,
                    unselectedIconColor = AppColors.RosaZazil,
                    indicatorColor = AppColors.RosaZazil
                )
            )
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController, productoVM: ProductoVM, usuarioVM: UsuarioVM, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = Pantallas.RUTA_HOME,
        modifier = modifier.fillMaxSize()
    ) {
        composable(Pantallas.RUTA_HOME) {
            Home()
        }
        composable(Pantallas.RUTA_CUENTA) {
            Cuenta(navController, usuarioVM, navigateTo = { route ->
                navController.navigate(route) {
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
            TiendaUno(navController, productoVM)
        }
        composable(Pantallas.RUTA_MI_INFORMACION) {
            MiInformacion()
        }
        composable(Pantallas.RUTA_DIRECCIONES) {
            Direcciones(usuarioVM)
        }
        composable(Pantallas.RUTA_TIENDA_DOS + "/{cat}") {
            val cat = it.arguments?.getString("cat")
            TiendaDos(navController, cat!!, productoVM)
        }
        composable(Pantallas.RUTA_TIENDA_TRES + "/{cat}") {
            val cat = it.arguments?.getString("cat")
            TiendaTres(navController, cat!!, productoVM)
        }
        composable(Pantallas.RUTA_DETALLE_PRODUCTO + "/{id}") {
            val id = it.arguments?.getString("id")
            DetalleProducto(id!!, productoVM)
        }
        composable(Pantallas.RUTA_SIGN_UP) {
            SignUp(navController, usuarioVM)
        }
        composable(Pantallas.RUTA_REGISTRAR) {
            Registrar(navController, usuarioVM)
        }
    }
}
