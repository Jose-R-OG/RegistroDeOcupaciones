package com.example.registrodeocupaciones.presentation.ocupaciones

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrodeocupaciones.domain.model.Ocupacion
import com.example.registrodeocupaciones.domain.usecase.UpsertOcupacionUseCase
import com.example.registrodeocupaciones.domain.usecase.validateDescripcion
import com.example.registrodeocupaciones.domain.usecase.validateSueldo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OcupacionViewModel @Inject constructor(
    private val upsertOcupacionUseCase: UpsertOcupacionUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(OcupacionUiState())
    val state = _state.asStateFlow()

    fun onEvent(event: OcupacionUiEvent) {
        when (event) {
            is OcupacionUiEvent.DescripcionChanged -> _state.update {
                it.copy(descripcion = event.value, descripcionError = null, errorGeneral = null)
            }
            is OcupacionUiEvent.SueldoChanged -> _state.update {
                it.copy(sueldo = event.value, sueldoError = null, errorGeneral = null)
            }
            OcupacionUiEvent.Save -> onSave()
            OcupacionUiEvent.Delete -> { /* Lógica de eliminar si fuera necesario */ }
        }
    }

    private fun onSave() {
        val descripcionVal = validateDescripcion(state.value.descripcion)
        val sueldoVal = validateSueldo(state.value.sueldo)

        if (!descripcionVal.isValid || !sueldoVal.isValid) {
            _state.update {
                it.copy(
                    descripcionError = descripcionVal.error,
                    sueldoError = sueldoVal.error
                )
            }
            return
        }

        viewModelScope.launch {
            _state.update { it.copy(isSaving = true) }
            val ocupacion = Ocupacion(
                ocupacionId = state.value.ocupacionId ?: 0,
                descripcion = state.value.descripcion,
                sueldo = state.value.sueldo.toDoubleOrNull() ?: 0.0
            )

            val result = upsertOcupacionUseCase(ocupacion)
            result.onSuccess {
                _state.update { it.copy(isSaving = false, saved = true) }
            }.onFailure { e ->
                _state.update { it.copy(isSaving = false, errorGeneral = e.message) }
            }
        }
    }
}