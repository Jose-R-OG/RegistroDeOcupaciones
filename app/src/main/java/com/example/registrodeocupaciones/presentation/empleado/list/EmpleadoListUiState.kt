package com.example.registrodeocupaciones.presentation.empleado.list

import com.example.registrodeocupaciones.domain.empleado.model.Empleado

class EmpleadoListUiState (
    val isLoading: Boolean = false,
    val ocupaciones: List<Empleado> = emptyList(),
    val message: String? = null,
    val navigateToCreate: Boolean = false,
    val navigateToEditId: Int? = null,
    val error: String? = null
)