package com.example.registrodeocupaciones.di

import com.example.registrodeocupaciones.data.repository.OcupacionRepositoryData
import com.example.registrodeocupaciones.domain.repository.OcupacionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindOcupacionRepository(impl: OcupacionRepositoryData): OcupacionRepository
}