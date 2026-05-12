package com.example.registrodeocupaciones.data.repository

import com.example.registrodeocupaciones.data.local.OcupacionDao
import com.example.registrodeocupaciones.data.mapper.toEntity
import com.example.registrodeocupaciones.data.mapper.toDomain
import com.example.registrodeocupaciones.domain.model.Ocupacion
import com.example.registrodeocupaciones.domain.repository.OcupacionRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class OcupacionRepositoryData @Inject constructor(private val localDataSource: OcupacionDao):
    OcupacionRepository {

    override fun observeOcupaciones(): Flow<List<Ocupacion>>
    {
        return  localDataSource.observeAll().map { entities -> entities.map { it.toDomain() } }
    }

    override suspend fun getOCupacion(id: Int): Ocupacion?
    {
        return localDataSource.getById(id)?.toDomain()
    }

    override suspend fun upsert(ocupacion: Ocupacion): Int
    {
        localDataSource.upsert(ocupacion.toEntity())
        return ocupacion.ocupacionId ?: 0
    }

    override suspend fun delete(id: Int)
    {
        localDataSource.deleteById(id)
    }

    override suspend fun exists(id: Int): Boolean
    {
        return localDataSource.exists(id)
    }

    override suspend fun existsByDescripcion(descripcion: String): Boolean
    {
        return localDataSource.existsByDescripcion(descripcion)
    }
}