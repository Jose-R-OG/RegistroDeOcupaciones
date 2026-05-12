package com.example.registrodeocupaciones.presentation.navigation

// El import correcto es este:
import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object OcupacionList : Screen()

    @Serializable
    data class OcupacionForm(val ocupacionId: Int) : Screen()
}