package com.example.registrodeocupaciones.domain.empleado.usecase

import com.example.registrodeocupaciones.domain.empleado.repository.EmpleadoRepository
import javax.inject.Inject

class DeleteEmpleadoUseCase @Inject constructor(
    private val repository: EmpleadoRepository
) {
    suspend operator fun invoke(id: Int) = repository.delete(id)
}
