package com.example.registrodeocupaciones.domain.horaExtra.usecase

import com.example.registrodeocupaciones.domain.horaExtra.repository.HoraExtraRepository

import javax.inject.Inject

class ObserveHorasExtrasUseCase @Inject constructor(
    private val repository: HoraExtraRepository
) {
    operator fun invoke() = repository.observeHorasExtras()
}