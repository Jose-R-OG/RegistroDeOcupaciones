package com.example.registrodeocupaciones.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute

// Importaciones de Pantallas de Horas Extras
import com.example.registrodeocupaciones.presentation.HoraExtra.list.HoraExtraListScreen
import com.example.registrodeocupaciones.presentation.HoraExtra.form.EditHoraExtraScreen

// Importaciones de Ocupaciones
import com.example.registrodeocupaciones.presentation.ocupaciones.list.OcupacionlistScreen
import com.example.registrodeocupaciones.presentation.ocupaciones.form.OcupacionFormScreen

// Importaciones de Empleados
import com.example.registrodeocupaciones.presentation.empleado.list.EmpleadoListScreen
import com.example.registrodeocupaciones.presentation.empleado.form.EmpleadoFormScreen

@Composable
fun MineNavHost(
    navController: NavHostController,
    innerPadding: PaddingValues,
    windowSizeClass: WindowSizeClass
) {
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController =  navController,
        startDestination = Screen.OcupacionList
    ) {
        // --- RUTAS DE OCUPACIONES ---
        composable<Screen.OcupacionList> {
            OcupacionlistScreen(
                windowSizeClass = windowSizeClass,
                onAddOcupacion = {
                    navController.navigate(Screen.OcupacionForm(ocupacionId = 0))
                },
                onEditOcupacion = { id ->
                    navController.navigate(Screen.OcupacionForm(ocupacionId = id))
                }
            )
        }

        composable<Screen.OcupacionForm> {
            OcupacionFormScreen(
                windowSizeClass = windowSizeClass,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        // --- RUTAS DE EMPLEADOS ---
        composable<Screen.EmpleadoList> {
            EmpleadoListScreen(
                windowSizeClass = windowSizeClass,
                onAddEmpleado = {
                    navController.navigate(Screen.EmpleadoForm(empleadoId = 0))
                },
                onEditEmpleado = { id ->
                    navController.navigate(Screen.EmpleadoForm(empleadoId = id))
                }
            )
        }

        composable<Screen.EmpleadoForm> {
            EmpleadoFormScreen(
                windowSizeClass = windowSizeClass,
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        // --- RUTAS DE HORAS EXTRAS ---
        composable<Screen.HoraExtraList> {
            HoraExtraListScreen(
                windowSizeClass = windowSizeClass,
                onDrawer = { },
                goToHoraExtra = { id -> navController.navigate(Screen.HoraExtra(id)) },
                createHoraExtra = { navController.navigate(Screen.HoraExtra(0)) }
            )
        }

        composable<Screen.HoraExtra> { backStackEntry ->
            val args = backStackEntry.toRoute<Screen.HoraExtra>()
            EditHoraExtraScreen(
                windowSizeClass = windowSizeClass,
                horaExtraId = args.horaExtraId,
                onNavigateBack = { navController.navigateUp() },
                onDrawer = { }
            )
        }
    }
}