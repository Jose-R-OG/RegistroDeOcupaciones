package com.example.registrodeocupaciones.domain.horaExtra.usecase

import com.example.registrodeocupaciones.domain.horaExtra.model.HoraExtra
import com.example.registrodeocupaciones.domain.horaExtra.repository.HoraExtraRepository
import javax.inject.Inject

class UpsertHoraExtraUseCase @Inject constructor(
    private val repository: HoraExtraRepository
) {
    suspend operator fun invoke(horaExtra: HoraExtra): Result<Int> {
        return try {
            val id = repository.upsert(horaExtra)
            Result.success(id)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}