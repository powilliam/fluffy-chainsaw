package com.powilliam.fluffychainsaw.ui.activities

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.WorkManager
import com.powilliam.fluffychainsaw.data.workers.MonthEndingWorker
import com.powilliam.fluffychainsaw.ui.graphs.FluffyChainsawGraph
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FluffyChainsawActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FluffyChainsawTheme {
                FluffyChainsawGraph()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            MonthEndingWorker.periodicWorkerName,
            ExistingPeriodicWorkPolicy.KEEP,
            MonthEndingWorker.periodicWorkRequest
        )
    }
}