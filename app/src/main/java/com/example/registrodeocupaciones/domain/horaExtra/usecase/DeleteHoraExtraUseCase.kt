package com.example.registrodeocupaciones.domain.horaExtra.usecase

import com.example.registrodeocupaciones.domain.horaExtra.repository.HoraExtraRepository
import javax.inject.Inject

class DeleteHoraExtraUseCase @Inject constructor(
    private val repository: HoraExtraRepository
) {
    suspend operator fun invoke(id: Int) = repository.delete(id)
}