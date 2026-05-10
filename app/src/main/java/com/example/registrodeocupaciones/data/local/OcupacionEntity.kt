package com.example.registrodeocupaciones.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ocupaciones") // Requerimiento: La tabla debe llamarse ocupaciones
data class OcupacionEntity(
    @PrimaryKey(autoGenerate = true)
    val ocupacionId: Int = 0,
    val descripcion: String, // Campo requerido
    val sueldo: Double      // Campo requerido
)