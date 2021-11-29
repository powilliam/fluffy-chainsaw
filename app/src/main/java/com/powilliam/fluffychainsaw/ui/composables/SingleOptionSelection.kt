package com.powilliam.fluffychainsaw.ui.composables

import android.content.res.Configuration
import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.RadioButtonChecked
import androidx.compose.material.icons.rounded.RadioButtonUnchecked
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme

data class Option<T>(val label: String, val value: T)

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun <T> SingleOptionSelection(
    modifier: Modifier = Modifier,
    label: (@Composable () -> Unit)? = null,
    selectedOption: Option<T>? = null,
    options: List<Option<T>> = emptyList(),
    onSelectOne: (Option<T>) -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }

    Surface(modifier.fillMaxWidth()) {
        Column {
            label?.let { composable ->
                Box(modifier.padding(vertical = 16.dp)) {
                    ProvideTextStyle(MaterialTheme.typography.labelLarge) {
                        composable()
                    }
                }
            }
            options.forEach { option ->
                Surface(modifier.fillMaxWidth()) {
                    Row(
                        modifier
                            .clickable(interactionSource, null) { onSelectOne(option) }
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val icon = if (selectedOption == option) {
                            Icons.Rounded.RadioButtonChecked
                        } else {
                            Icons.Rounded.RadioButtonUnchecked
                        }

                        Text(
                            text = option.label,
                            style = MaterialTheme.typography.bodyLarge.copy(
                                color = MaterialTheme.colorScheme.onSurface.copy(
                                    alpha = 0.8F
                                )
                            )
                        )

                        AnimatedContent(
                            targetState = icon,
                            transitionSpec = { fadeIn() with fadeOut() }
                        ) { imageVector ->
                            Icon(
                                imageVector = imageVector,
                                contentDescription = null,
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun SingleOptionSelectionPreview() {
    val options = listOf(
        Option(label = "Fixed", value = ExpenseType.Fixed),
        Option(label = "Variable", value = ExpenseType.Variable)
    )

    val (option, optionSet) = remember {
        mutableStateOf(options.first())
    }

    FluffyChainsawTheme {
        Column {
            SingleOptionSelection(
                label = {
                    Text(text = "Expense Type")
                },
                options = options,
            ) {}
            Spacer(Modifier.height(24.dp))
            SingleOptionSelection(
                options = options,
                selectedOption = option
            ) { selected ->
                optionSet(selected)
            }
        }
    }
}