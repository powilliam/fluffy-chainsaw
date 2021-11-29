package com.powilliam.fluffychainsaw.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.powilliam.fluffychainsaw.ui.graphs.FluffyChainsawGraph
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FluffyChainsawActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FluffyChainsawTheme {
                FluffyChainsawGraph()
            }
        }
    }
}