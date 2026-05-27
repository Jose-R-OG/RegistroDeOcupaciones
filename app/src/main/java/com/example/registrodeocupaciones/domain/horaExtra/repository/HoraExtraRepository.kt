package com.example.registrodeocupaciones.domain.horaExtra.repository

import com.example.registrodeocupaciones.domain.horaExtra.model.HoraExtra
import kotlinx.coroutines.flow.Flow

interface HoraExtraRepository {
    fun observeHorasExtras(): Flow<List<HoraExtra>>
    fun observeByEmpleado(empleadoId: Int): Flow<List<HoraExtra>>
    suspend fun getHoraExtra(id: Int): HoraExtra?
    suspend fun upsert(horaExtra: HoraExtra): Int
    suspend fun delete(id: Int)
}