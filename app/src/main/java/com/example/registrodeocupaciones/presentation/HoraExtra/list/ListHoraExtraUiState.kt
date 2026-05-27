package com.example.registrodeocupaciones.presentation.HoraExtra.list

import com.example.registrodeocupaciones.domain.horaExtra.model.HoraExtra

data class ListHoraExtraUiState(
    val isLoading: Boolean = false,
    val horasExtras: List<HoraExtra> = emptyList(),
    val message: String? = null
)