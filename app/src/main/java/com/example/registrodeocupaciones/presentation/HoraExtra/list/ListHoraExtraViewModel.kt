package com.example.registrodeocupaciones.presentation.HoraExtra.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registrodeocupaciones.domain.horaExtra.usecase.ObserveHorasExtrasUseCase
import com.example.registrodeocupaciones.domain.horaExtra.usecase.DeleteHoraExtraUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListHoraExtraViewModel @Inject constructor(
    private val observeHorasExtras: ObserveHorasExtrasUseCase,
    private val deleteHoraExtra: DeleteHoraExtraUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(ListHoraExtraUiState(isLoading = true))
    val state: StateFlow<ListHoraExtraUiState> = _state.asStateFlow()

    init { load() }

    fun onEvent(event: ListHoraExtraUiEvent) {
        when (event) {
            ListHoraExtraUiEvent.Load -> load()
            is ListHoraExtraUiEvent.Delete -> delete(event.id)
        }
    }

    private fun load() {
        viewModelScope.launch {
            observeHorasExtras().collectLatest { list ->
                _state.update { it.copy(isLoading = false, horasExtras = list) }
            }
        }
    }

    private fun delete(id: Int) {
        viewModelScope.launch {
            try {
                deleteHoraExtra(id)
                _state.update { it.copy(message = "Hora extra eliminada") }
            } catch (e: Exception) {
                _state.update { it.copy(message = "Error: ${e.message}") }
            }
        }
    }
}