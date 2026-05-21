package com.example.registrodeocupaciones.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.registrodeocupaciones.data.empleado.local.Converters
import com.example.registrodeocupaciones.data.empleado.local.EmpleadoDao
import com.example.registrodeocupaciones.data.empleado.local.EmpleadoEntity
import com.example.registrodeocupaciones.data.local.OcupacionDao
import com.example.registrodeocupaciones.data.local.OcupacionEntity

@Database(
    entities = [OcupacionEntity::class, EmpleadoEntity::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(Converters::class) // <-- 3. AGREGA ESTA LÍNEA AQUÍ
abstract class OcupacionDb : RoomDatabase() {
    abstract fun ocupacionDao(): OcupacionDao
    abstract fun empleadoDao(): EmpleadoDao
}