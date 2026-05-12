package com.example.registrodeocupaciones.domain.usecase

import com.example.registrodeocupaciones.domain.model.Ocupacion
import com.example.registrodeocupaciones.domain.repository.OcupacionRepository
import javax.inject.Inject

class GetOcupacionUseCase @Inject constructor(private val repository: OcupacionRepository){
    suspend operator fun invoke(id: Int): Ocupacion? = repository.getOCupacion(id)
}