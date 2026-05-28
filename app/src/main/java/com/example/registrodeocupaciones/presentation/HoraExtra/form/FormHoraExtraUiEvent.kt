package com.example.registrodeocupaciones.presentation.HoraExtra.form

sealed interface FormHoraExtraUiEvent {
    data class Load(val id: Int?) : FormHoraExtraUiEvent
    data class EmpleadoChanged(val empleadoId: Int) : FormHoraExtraUiEvent
    data class FechaDesdeChanged(val value: Long?) : FormHoraExtraUiEvent
    data class FechaHastaChanged(val value: Long?) : FormHoraExtraUiEvent
    data class HorasTotalesChanged(val value: String) : FormHoraExtraUiEvent
    data class HorasNocturnasChanged(val value: String) : FormHoraExtraUiEvent
    data object Save : FormHoraExtraUiEvent
    data object Delete : FormHoraExtraUiEvent
}