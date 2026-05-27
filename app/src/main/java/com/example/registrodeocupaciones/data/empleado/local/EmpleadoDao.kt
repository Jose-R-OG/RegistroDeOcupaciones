package com.example.registrodeocupaciones.data.empleado.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface EmpleadoDao {
    @Upsert
    suspend fun upsert(entity: EmpleadoEntity)
    @Delete
    suspend fun delete(entity: EmpleadoEntity)
    @Query(value = "Select * from empleados")
    fun observeAll(): Flow<List<EmpleadoEntity>>
    @Query(value = "Select * from empleados Where empleadoId =:id")
    suspend fun getById(id: Int): EmpleadoEntity?
    @Query(value = "Delete from empleados Where empleadoId =:id")
    suspend fun deleteById(id: Int)
    @Query(value = "select exists (select 1 from empleados Where empleadoId =:id)")
    suspend fun exists(id: Int): Boolean

    @Query("SELECT * FROM empleados WHERE nombres = :nombres")
    suspend fun getByNombres(nombres: String): List<EmpleadoEntity>

}