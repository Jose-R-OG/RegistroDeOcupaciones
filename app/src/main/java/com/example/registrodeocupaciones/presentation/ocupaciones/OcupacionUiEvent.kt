package com.example.registrodeocupaciones.presentation.ocupaciones

sealed interface OcupacionUiEvent {
    data class DescripcionChanged(val value: String) : OcupacionUiEvent
    data class SueldoChanged(val value: String) : OcupacionUiEvent
    data object Save : OcupacionUiEvent
    data object Delete : OcupacionUiEvent
}