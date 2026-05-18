package com.example.registrodeocupaciones.presentation.empleado.form

import java.time.LocalDate

sealed interface EmpleadoFormUiEvent {
    data class Load(val id: Int?): EmpleadoFormUiEvent
    data class FechaIngresoChanged(val value: LocalDate): EmpleadoFormUiEvent
    data class NombresChanged(val value: String): EmpleadoFormUiEvent
    data class SexoChanged(val value: String): EmpleadoFormUiEvent

    data class SueldoChanged(val value: String): EmpleadoFormUiEvent
    data object Save: EmpleadoFormUiEvent
    data object Delete: EmpleadoFormUiEvent
}