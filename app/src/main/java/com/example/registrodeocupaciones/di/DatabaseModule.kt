package com.example.registrodeocupaciones.di

import android.content.Context
import androidx.room.Room
import com.example.registrodeocupaciones.data.local.OcupacionDao
import com.example.registrodeocupaciones.data.database.OcupacionDb
import com.example.registrodeocupaciones.data.empleado.local.EmpleadoDao
import com.example.registrodeocupaciones.data.horasExtras.local.HoraExtraDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun ProvideOcupacionDatabase(@ApplicationContext context: Context): OcupacionDb
    {
        return Room.databaseBuilder(
            context,
            OcupacionDb::class.java,
            "Ocupacion.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideOcupacionDao(database: OcupacionDb): OcupacionDao
    {
        return database.ocupacionDao()
    }

    @Provides
    fun provideEmpleadoDao(db: OcupacionDb): EmpleadoDao {
        return db.empleadoDao()
    }

    @Provides
    @Singleton
    fun provideHoraExtraDao(database: OcupacionDb): HoraExtraDao
    {
        return database.horaExtraDao()
    }
}