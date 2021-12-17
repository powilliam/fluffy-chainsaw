package com.powilliam.fluffychainsaw.ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.powilliam.fluffychainsaw.R
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme

@Composable
fun TextField(
    modifier: Modifier = Modifier,
    singleLine: Boolean = true,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    value: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {
    Box(modifier.fillMaxWidth()) {
        BasicTextField(
            modifier = modifier
                .fillMaxWidth()
                .align(Alignment.CenterStart),
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle.copy(color = MaterialTheme.colorScheme.onSurface),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
            singleLine = singleLine,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )

        if (value.isEmpty()) {
            Text(
                modifier = modifier.align(Alignment.CenterStart),
                text = placeholder,
                style = textStyle
            )
        }
    }
}

@Composable
fun ContainedTextField(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(12.dp),
    color: Color = MaterialTheme.colorScheme.surface,
    tonalElevation: Dp = 1.dp,
    singleLine: Boolean = true,
    leading: @Composable() (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    value: String,
    placeholder: String? = null,
    onValueChange: (String) -> Unit
) {
    Surface(
        modifier.fillMaxWidth(),
        shape = shape,
        color = color,
        tonalElevation = tonalElevation
    ) {
        Box(
            modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 56.dp)
                .padding(horizontal = 16.dp, vertical = 16.dp)
        ) {
            BasicTextField(
                modifier = modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterStart),
                value = value,
                onValueChange = onValueChange,
                textStyle = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.onSurface
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                keyboardOptions = keyboardOptions,
                keyboardActions = keyboardActions,
                singleLine = singleLine,
                decorationBox = { innerTextField ->
                    ContainedTextFieldDecoration(leading = leading) {
                        innerTextField()
                    }
                }
            )
            if (value.isEmpty() && placeholder != null) {
                Text(
                    modifier = modifier
                        .align(Alignment.CenterStart)
                        .padding(start = if (leading != null) 40.dp else 0.dp),
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface
                    )
                )
            }
        }
    }
}

@Composable
private fun ContainedTextFieldDecoration(
    modifier: Modifier = Modifier,
    leading: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit
) {
    Row(
        modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        leading?.let { composable ->
            composable()
            Spacer(modifier.width(16.dp))
        }
        content()
    }
}

@Composable
fun SearchTextField(value: String, onValueChange: (String) -> Unit) {
    ContainedTextField(
        value = value,
        placeholder = stringResource(R.string.expenses_search_placeholder),
        shape = RoundedCornerShape(0.dp),
        tonalElevation = 0.dp,
        onValueChange = onValueChange,
        leading = {
            Icon(
                imageVector = Icons.Rounded.Search,
                contentDescription = null
            )
        },
    )
}

@Preview
@Composable
private fun TextFieldPreview() {
    FluffyChainsawTheme {
        TextField(value = "", placeholder = "Hello World") {}
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

@Preview
@Composable
private fun ContainedTextFieldWithLeadingAndPreview() {
    FluffyChainsawTheme {
        ContainedTextField(
            value = "",
            placeholder = "Search",
            leading = {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
            },
        ) {}
    }
}

@Preview
@Composable
private fun ContainedTextFieldWithLeadingAndWithoutPlaceholderPreview() {
    FluffyChainsawTheme {
        ContainedTextField(
            value = "",
            leading = {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
            },
        ) {}
    }
}

@Preview
@Composable
private fun SearchTextFieldPreview() {
    FluffyChainsawTheme {
        SearchTextField(value = "", onValueChange = {})
    }
}