package com.greeshma.smartpocket.di

import com.greeshma.smartpocket.domain.repository.PocketRepository
import com.greeshma.smartpocket.domain.repository.PocketRepositoryImpl
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
    abstract fun bindPocketRepository(impl: PocketRepositoryImpl): PocketRepository
}