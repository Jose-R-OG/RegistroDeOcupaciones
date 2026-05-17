package com.example.registrodeocupaciones.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.registrodeocupaciones.data.local.OcupacionDao
import com.example.registrodeocupaciones.data.local.OcupacionEntity

@Database(
    entities = [OcupacionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class OcupacionDb : RoomDatabase() {
    abstract fun ocupacionDao(): OcupacionDao
}