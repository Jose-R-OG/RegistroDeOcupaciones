package com.example.registrodeocupaciones.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Importaciones de Ocupaciones
import com.example.registrodeocupaciones.presentation.ocupaciones.list.OcupacionlistScreen
import com.example.registrodeocupaciones.presentation.ocupaciones.form.OcupacionFormScreen

// 1. Agrega las importaciones de las pantallas de Empleados
import com.example.registrodeocupaciones.presentation.empleado.list.EmpleadoListScreen
import com.example.registrodeocupaciones.presentation.empleado.form.EmpleadoFormScreen

@Composable
fun MineNavHost(
    navController: NavHostController = rememberNavController(),
    innerPadding: PaddingValues
) {
    NavHost(
        modifier = Modifier.padding(innerPadding),
        navController =  navController,
        startDestination = Screen.OcupacionList
    ) {
        // --- RUTAS DE OCUPACIONES ---
        composable<Screen.OcupacionList> {
            OcupacionlistScreen(
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
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }

        // --- RUTAS DE EMPLEADOS (AGREGADO) ---
        composable<Screen.EmpleadoList> {
            EmpleadoListScreen(
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
                onNavigateBack = {
                    navController.navigateUp()
                }
            )
        }
    }
}