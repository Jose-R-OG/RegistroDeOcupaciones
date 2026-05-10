package com.example.registrodeocupaciones.domain.usecase

import com.example.registrodeocupaciones.data.mapper.toEntity
import com.example.registrodeocupaciones.data.repository.OcupacionRepository
import com.example.registrodeocupaciones.domain.model.Ocupacion
import javax.inject.Inject

class UpsertOcupacionUseCase @Inject constructor(
    private val repository: OcupacionRepository
) {
    suspend operator fun invoke(ocupacion: Ocupacion): Result<Unit> {
        val descRes = validateDescripcion(ocupacion.descripcion)
        if (!descRes.isValid) return Result.failure(Exception(descRes.error))

        val sueldoRes = validateSueldo(ocupacion.sueldo.toString())
        if (!sueldoRes.isValid) return Result.failure(Exception(sueldoRes.error))

        val existe = repository.getByDescripcion(ocupacion.descripcion)
        if (existe != null && existe.ocupacionId != ocupacion.ocupacionId) {
            return Result.failure(Exception("Ya existe una ocupación con esta descripción"))
        }

        return runCatching {
            repository.upsert(ocupacion.toEntity())
        }
    }
}