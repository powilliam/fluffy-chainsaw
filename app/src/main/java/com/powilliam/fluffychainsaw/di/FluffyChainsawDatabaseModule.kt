package com.powilliam.fluffychainsaw.di

import android.content.Context
import androidx.room.Room
import com.powilliam.fluffychainsaw.data.daos.ExpenseDao
import com.powilliam.fluffychainsaw.data.databases.FluffyChainsawDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FluffyChainsawDatabaseModule {
    @Singleton
    @Provides
    fun provideFluffyChainsawDatabase(
        @ApplicationContext context: Context
    ): FluffyChainsawDatabase {
        return Room.databaseBuilder(
            context,
            FluffyChainsawDatabase::class.java,
            "fluffychainsaw.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideExpenseDao(database: FluffyChainsawDatabase): ExpenseDao {
        return database.expenseDao()
    }
}