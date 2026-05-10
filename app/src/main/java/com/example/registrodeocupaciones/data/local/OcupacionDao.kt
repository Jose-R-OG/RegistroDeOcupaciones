package com.example.registrodeocupaciones.data.local

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface OcupacionDao {
    @Upsert
    suspend fun upsert(ocupacion: OcupacionEntity)

    @Delete
    suspend fun delete(ocupacion: OcupacionEntity)

    @Query("SELECT * FROM Ocupaciones WHERE ocupacionId = :id")
    suspend fun getById(id: Int): OcupacionEntity?

    @Query("SELECT * FROM Ocupaciones WHERE descripcion = :descripcion LIMIT 1")
    suspend fun getByDescripcion(descripcion: String): OcupacionEntity?

    @Query("SELECT * FROM Ocupaciones")
    fun getAll(): Flow<List<OcupacionEntity>>
}