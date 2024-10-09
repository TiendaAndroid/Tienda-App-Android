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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.larc.appandroid.model.NetworkManager
import com.larc.appandroid.ui.theme.AppAndroidTheme
import com.larc.appandroid.viewmodel.CarritoVM
import com.larc.appandroid.viewmodel.DireccionVM
import com.larc.appandroid.viewmodel.NetworkManagerVM
import com.larc.appandroid.viewmodel.PaymentsVM
import com.larc.appandroid.viewmodel.ProductoVM
import com.larc.appandroid.viewmodel.UsuarioVM
import com.stripe.android.paymentsheet.PaymentSheet

/**
 * Representa la vista principal de la aplicación, con sus barras superior e inferior.
 * @author Arturo Barrios Mendoza, Lucio Arturo Reyes Castillo, Fidel Alexander Bonilla Montalvo, Vicente Jesús Ramos Chávez
 */

@Composable
fun AppPrincipal(navController: NavHostController, paymentSheet: PaymentSheet) {
    //val navController = rememberNavController()
    val productoVM: ProductoVM = viewModel()
    val usuarioVM: UsuarioVM = viewModel()
    val direccionVM: DireccionVM = viewModel()
    val carritoVM: CarritoVM = viewModel()
    val paymentsViewModel: PaymentsVM = viewModel()
    val networkVM: NetworkManagerVM = viewModel()
    var searchText by remember { mutableStateOf("") }

    val connection = networkVM.checkNetworkConnection()

    if (!connection) {
        Box(
            modifier = Modifier.fillMaxSize()
                .background(Color(0xFFFAF8FF)),
            contentAlignment = Alignment.Center

        ) {
            Text(text = "No tienes conexión. Te sugerimos revisar la conexión de tu dispositivo para disfrutar de la aplicación.",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
                    .padding(16.dp))
        }
    } else {

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
                AppNavHost(
                    navController,
                    productoVM,
                    usuarioVM,
                    direccionVM,
                    carritoVM,
                    paymentsViewModel,
                    paymentSheet,
                    modifier = Modifier.padding(innerPadding)
                )
            }
        }
    }
}

// Barra superior (de búsqueda)
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
                    .padding(top = 28.dp)//12.dp)
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
                modifier = Modifier.padding(top = 32.dp)
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
                modifier = Modifier.background(Color(0xFFFAF8FF))
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
                /*
                DropdownMenuItem(
                    text = { Text("Testimonios", color = AppColors.GrisOscuro) },
                    onClick = {
                        expanded = false
                        navigateTo(navController, Pantallas.RUTA_TESTIMONIOS)
                    }
                )
                 */
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
            containerColor = Color(0xFFFAF8FF)
        )
    )
}

// Función para navegar
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

// Barra inferior (para navegar)
@Composable
fun AppBottomBar(navController: NavHostController) {
    BottomAppBar(
        containerColor = Color(0xFFFAF8FF)
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

// Navegación entre pantallas
@Composable
fun AppNavHost(navController: NavHostController,
               productoVM: ProductoVM,
               usuarioVM: UsuarioVM,
               direccionVM: DireccionVM,
               carritoVM: CarritoVM,
               paymentsViewModel: PaymentsVM,
               paymentSheet: PaymentSheet,
               modifier: Modifier = Modifier) {
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
            Carrito(navController, carritoVM, usuarioVM)
        }
        composable(Pantallas.RUTA_MIS_PEDIDOS) {
            MisPedidos(navController, usuarioVM)
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
            TiendaUno(navController, productoVM, usuarioVM)
        }
        composable(Pantallas.RUTA_MI_INFORMACION) {
            MiInformacion()
        }
        composable(Pantallas.RUTA_DIRECCIONES) {
            Direcciones(navController, usuarioVM)
        }
        composable(Pantallas.RUTA_TIENDA_DOS + "/{cat}") {
            val cat = it.arguments?.getString("cat")
            TiendaDos(navController, cat!!, productoVM, usuarioVM, carritoVM)
        }
        composable(Pantallas.RUTA_TIENDA_TRES + "/{cat}") {
            val cat = it.arguments?.getString("cat")
            TiendaTres(navController, cat!!, productoVM, usuarioVM, carritoVM)
        }
        composable(Pantallas.RUTA_DETALLE_PRODUCTO + "/{id}" + "/{cartId}") {
            val id = it.arguments?.getString("id")
            val cartId = it.arguments?.getString("cartId")
            DetalleProducto(id!!, cartId!!, productoVM, carritoVM)
        }
        composable(Pantallas.RUTA_SIGN_UP) {
            SignUp(navController, usuarioVM)
        }
        composable(Pantallas.RUTA_REGISTRAR) {
            Registrar(navController, usuarioVM)
        }
        composable(Pantallas.RUTA_NUEVA_DIRECCION + "/{token}") {
            val token = it.arguments?.getString("token")
            NuevaDireccion(navController, token!!, direccionVM)
        }
        composable(Pantallas.RUTA_ELIMINAR_DIRECCION + "/{token}/{addressId}") {
            val token = it.arguments?.getString("token")
            val addressId = it.arguments?.getString("addressId")
            EliminarDireccion(navController, token!!, addressId!!, direccionVM)
        }
        composable("payment_screen" + "/{total}") {
            val total = it.arguments?.getString("total")
            if (total != null) {
                val totalAmount = total.toDouble()
                PaymentScreen(navController, paymentsViewModel, usuarioVM, carritoVM, amount = totalAmount, paymentSheet = paymentSheet)
            }
        }
        composable(Pantallas.RUTA_DIRECCION_ENTREGA + "/{total}") {
            val total = it.arguments?.getString("total")
            DireccionEntrega(navController, usuarioVM, paymentsViewModel, total!!.toDouble())
        }
    }
}
