package com.example.registrodeocupaciones.domain.usecase

import com.example.registrodeocupaciones.data.mapper.toEntity
import com.example.registrodeocupaciones.domain.model.Ocupacion
import com.example.registrodeocupaciones.domain.repository.OcupacionRepository
import javax.inject.Inject
import kotlin.compareTo

class UpsertOcupacionUseCase @Inject constructor(private val repository: OcupacionRepository) {
    suspend operator fun invoke(ocupacion: Ocupacion): Result<Int>
    {
        if(ocupacion.descripcion.isBlank())
        {
            return Result.failure(IllegalArgumentException("La Descripcion es campo Obligatorio"))
        }

        if(ocupacion.sueldo <= 0.0)
        {
            return Result.failure(IllegalArgumentException("El sueldo debe ser mayor a cero"))
        }

        val existe = repository.existsByDescripcion(ocupacion.descripcion.trim())

        if(existe && ocupacion.ocupacionId == 0)
        {
            return Result.failure(IllegalArgumentException("Ya existe ocupacion con esta descripcion"))
        }

        return runCatching { repository.upsert(ocupacion) }
    }
}