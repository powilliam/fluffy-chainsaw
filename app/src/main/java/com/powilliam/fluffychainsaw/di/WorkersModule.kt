package com.powilliam.fluffychainsaw.di

import androidx.work.BackoffPolicy
import androidx.work.PeriodicWorkRequest
import androidx.work.PeriodicWorkRequestBuilder
import com.powilliam.fluffychainsaw.data.workers.MonthEndingWorker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PeriodicMonthEndingWorkRequest

@Module
@InstallIn(SingletonComponent::class)
object WorkersModule {
    @PeriodicMonthEndingWorkRequest
    @Provides
    fun providePeriodicMonthEndingWorkRequest(): PeriodicWorkRequest =
        PeriodicWorkRequestBuilder<MonthEndingWorker>(15, TimeUnit.MINUTES)
            .setBackoffCriteria(BackoffPolicy.LINEAR, 15, TimeUnit.SECONDS)
            .addTag(MonthEndingWorker.workerTag)
            .build()
}