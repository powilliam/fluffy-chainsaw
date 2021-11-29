package com.powilliam.fluffychainsaw.ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme

@Composable
fun ContainedTextField(
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    value: String,
    placeholder: String = "",
    onValueChange: (String) -> Unit
) {
    Surface(
        modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        color = MaterialTheme.colorScheme.surface,
        tonalElevation = 1.dp
    ) {
        Box(
            modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            BasicTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
                value = value,
                onValueChange = onValueChange,
                textStyle = MaterialTheme.typography.bodyLarge,
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions
            )
            if (value.isEmpty()) {
                Text(
                    modifier = modifier.align(Alignment.CenterStart),
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface.copy(
                            alpha = 0.4F
                        )
                    )
                )
            }
        }
    }
}

@Preview
@Composable
private fun ContainedTextFieldPreview() {
    FluffyChainsawTheme {
        ContainedTextField(
            value = "",
            placeholder = "Placeholder"
        ) {}
    }
}