package com.example.registrodeocupaciones.domain.repository

import com.example.registrodeocupaciones.domain.model.Ocupacion
import kotlinx.coroutines.flow.Flow

interface OcupacionRepository {
    fun observeOcupaciones(): Flow<List<Ocupacion>>
    suspend fun getOCupacion(id: Int): Ocupacion?
    suspend fun  upsert(ocupacion: Ocupacion): Int
    suspend fun  delete(id: Int)
    suspend fun  exists(id: Int): Boolean
    suspend fun  existsByDescripcion(descripcion: String): Boolean
}