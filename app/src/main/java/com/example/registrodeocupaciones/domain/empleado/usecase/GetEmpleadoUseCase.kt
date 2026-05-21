package com.example.registrodeocupaciones.domain.empleado.usecase

import com.example.registrodeocupaciones.domain.empleado.model.Empleado
import com.example.registrodeocupaciones.domain.empleado.repository.EmpleadoRepository
import javax.inject.Inject

class GetEmpleadoUseCase @Inject constructor(private val repository: EmpleadoRepository) {
    suspend operator fun invoke(id: Int): Empleado? = repository.getEmpleado(id)
}