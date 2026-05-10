package com.example.registrodeocupaciones.data.repository

import com.example.registrodeocupaciones.data.local.OcupacionDao
import com.example.registrodeocupaciones.data.local.OcupacionEntity
import javax.inject.Inject

// El repositorio centraliza el acceso a los datos
class OcupacionRepository @Inject constructor(
    private val ocupacionDao: OcupacionDao
) {
    // Función para insertar o actualizar
    suspend fun upsert(ocupacion: OcupacionEntity) = ocupacionDao.upsert(ocupacion)

    // Función para borrar
    suspend fun delete(ocupacion: OcupacionEntity) = ocupacionDao.delete(ocupacion)

    // Función para buscar por ID
    suspend fun getById(id: Int) = ocupacionDao.getById(id)

    // Función vital para el requerimiento de no duplicados
    suspend fun getByDescripcion(descripcion: String) = ocupacionDao.getByDescripcion(descripcion)

    // Función para obtener la lista completa de ocupaciones
    fun getAll() = ocupacionDao.getAll()
}