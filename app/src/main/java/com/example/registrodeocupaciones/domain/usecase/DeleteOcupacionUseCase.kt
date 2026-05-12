package com.example.registrodeocupaciones.domain.usecase

import com.example.registrodeocupaciones.domain.repository.OcupacionRepository
import javax.inject.Inject

class DeleteOcupacionUseCase @Inject constructor(private val repository: OcupacionRepository){
    suspend operator fun invoke(id: Int) = repository.delete(id)
}