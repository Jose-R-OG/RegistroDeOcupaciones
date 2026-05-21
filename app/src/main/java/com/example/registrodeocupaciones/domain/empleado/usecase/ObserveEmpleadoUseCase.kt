package com.example.registrodeocupaciones.domain.empleado.usecase

import com.example.registrodeocupaciones.domain.empleado.model.Empleado
import com.example.registrodeocupaciones.domain.empleado.repository.EmpleadoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveEmpleadoUseCase@Inject constructor(private val repository: EmpleadoRepository) {
    operator fun invoke(): Flow<List<Empleado>> = repository.observeEmpleados()
}