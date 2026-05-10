package com.example.registrodeocupaciones.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [OcupacionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class OcupacionDb : RoomDatabase() {
    abstract fun ocupacionDao(): OcupacionDao
}