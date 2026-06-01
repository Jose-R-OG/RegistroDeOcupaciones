package com.example.registrodeocupaciones

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.registrodeocupaciones.presentation.navigation.MineNavHost
import com.example.registrodeocupaciones.presentation.navigation.Screen
import com.example.registrodeocupaciones.ui.theme.RegistroDeOcupacionesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RegistroDeOcupacionesTheme {

                val windowSizeClass = calculateWindowSizeClass(this)
                val navController = rememberNavController()

                val items = listOf(
                    TopLevelRoute("Empleado", Screen.EmpleadoList, Icons.Default.Person),
                    TopLevelRoute("Ocupacion", Screen.OcupacionList, Icons.Default.Home),
                    TopLevelRoute("Horas Extras", Screen.HoraExtraList, Icons.Default.Add)
                )

                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination


                val isCompactScreen = windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact

                Scaffold(
                    bottomBar = {

                        if (isCompactScreen) {
                            NavigationBar {
                                items.forEach { item ->
                                    NavigationBarItem(
                                        icon = { Icon(item.icono, contentDescription = item.nombre) },
                                        label = { Text(item.nombre) },
                                        selected = currentDestination?.hierarchy?.any {
                                            it.hasRoute(item.ruta::class)
                                        } == true,
                                        onClick = {
                                            navController.navigate(item.ruta) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->

                    if (isCompactScreen) {

                        MineNavHost(
                            navController = navController,
                            innerPadding = innerPadding,
                            windowSizeClass = windowSizeClass
                        )
                    } else {

                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(innerPadding)
                        ) {
                            NavigationRail {
                                items.forEach { item ->
                                    NavigationRailItem(
                                        icon = { Icon(item.icono, contentDescription = item.nombre) },
                                        label = { Text(item.nombre) },
                                        selected = currentDestination?.hierarchy?.any {
                                            it.hasRoute(item.ruta::class)
                                        } == true,
                                        onClick = {
                                            navController.navigate(item.ruta) {
                                                popUpTo(navController.graph.findStartDestination().id) {
                                                    saveState = true
                                                }
                                                launchSingleTop = true
                                                restoreState = true
                                            }
                                        }
                                    )
                                }
                            }

                            MineNavHost(
                                navController = navController,
                                innerPadding = PaddingValues(0.dp),
                                windowSizeClass = windowSizeClass
                            )
                        }
                    }
                }
            }
        }
    }
}

data class TopLevelRoute<T : Any>(
    val nombre: String,
    val ruta: T,
    val icono: androidx.compose.ui.graphics.vector.ImageVector
)