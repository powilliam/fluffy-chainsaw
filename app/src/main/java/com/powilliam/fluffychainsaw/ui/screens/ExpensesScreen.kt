package com.powilliam.fluffychainsaw.ui.screens

import android.content.res.Configuration
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExpensesScreen() {
    Scaffold {
        Text(text = "Expenses")
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ExpensesScreenPreview() {
    FluffyChainsawTheme {
        ExpensesScreen()
    }
}