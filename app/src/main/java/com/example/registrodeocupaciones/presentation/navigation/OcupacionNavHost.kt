package com.example.registrodeocupaciones.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.registrodeocupaciones.presentation.ocupaciones.OcupacionFormScreen

@Composable
fun OcupacionNavHost(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.OcupacionForm(0) // Empezamos en el registro
    ) {
        composable<Screen.OcupacionForm> {
            OcupacionFormScreen(
                onBack = { navController.navigateUp() }
            )
        }
        // Aquí podrías agregar la pantalla de lista más adelante
    }
}