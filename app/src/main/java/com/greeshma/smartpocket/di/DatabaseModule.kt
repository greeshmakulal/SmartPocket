package com.greeshma.smartpocket.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.greeshma.smartpocket.data.local.PocketDataBase
import com.greeshma.smartpocket.data.local.PocketItemDao
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
    fun provideRoomInstance(@ApplicationContext context: Context): PocketDataBase
    {
        return  Room.databaseBuilder(
            context = context,
            klass = PocketDataBase::class.java,
            name = "smart_pocket"
        ).fallbackToDestructiveMigration(true)
            .build()
    }


    @Provides
    @Singleton
    fun providePocketDao(database: PocketDataBase): PocketItemDao
    {
        return database.getPocketDao()
    }
}