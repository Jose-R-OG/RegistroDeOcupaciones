package com.example.registrodeocupaciones.domain.usecase

import com.example.registrodeocupaciones.domain.model.Ocupacion
import com.example.registrodeocupaciones.domain.repository.OcupacionRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveOcupacionUseCase @Inject constructor(private val repository: OcupacionRepository) {
    operator fun invoke(): Flow<List<Ocupacion>> = repository.observeOcupaciones()
}