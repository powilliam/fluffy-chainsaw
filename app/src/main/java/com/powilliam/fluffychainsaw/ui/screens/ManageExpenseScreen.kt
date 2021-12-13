package com.powilliam.fluffychainsaw.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme
import com.powilliam.fluffychainsaw.R
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import com.powilliam.fluffychainsaw.data.entities.stringResourceId
import com.powilliam.fluffychainsaw.ui.composables.*
import com.powilliam.fluffychainsaw.ui.viewmodels.ManageExpenseUiState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun ManageExpenseScreen(
    uiState: ManageExpenseUiState = ManageExpenseUiState(),
    onChangeName: (String) -> Unit = {},
    onChangeCost: (String) -> Unit = {},
    onChangeType: (ExpenseType) -> Unit = {},
    onToggleIsSelectingOneType: () -> Unit = {},
    onDelete: () -> Unit = {},
    onCancel: () -> Unit = {},
    onDone: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    val softwareKeyboardController = LocalSoftwareKeyboardController.current

    if (uiState.isSelectingOneType) {
        AlertDialog(
            onDismissRequest = onToggleIsSelectingOneType,
            title = {
                Text(text = stringResource(R.string.manage_expense_type_placeholder))
            },
            text = {
                SingleOptionSelection(
                    selectedOption = uiState.type,
                    options = listOf(
                        Option(
                            label = stringResource(R.string.expense_type_variable),
                            value = ExpenseType.Variable
                        ),
                        Option(
                            label = stringResource(R.string.expense_type_fixed),
                            value = ExpenseType.Fixed
                        )
                    ),
                    onSelectOne = { option -> onChangeType(option.value) },
                )
            },
            confirmButton = {
                TextButton(onClick = onToggleIsSelectingOneType) {
                    Text(text = stringResource(R.string.manage_expense_done_button))
                }
            }
        )
    }

    Scaffold(
        topBar = {
            ManageExpenseScreenTopAppBar(
                canSubmit = uiState.canSubmit,
                canDelete = uiState.canDelete,
                onCancel = onCancel,
                onDelete = onDelete,
                onDone = onDone
            )
        }
    ) {
        Column {
            ActionLayout {
                TextField(
                    value = uiState.name,
                    textStyle = MaterialTheme.typography.headlineSmall,
                    placeholder = stringResource(R.string.manage_expense_name_placeholder),
                    onValueChange = onChangeName,
                )
            }
            ActionLayout(
                icon = {
                    Icon(imageVector = Icons.Rounded.AttachMoney, contentDescription = null)
                }
            ) {
                TextField(
                    value = uiState.cost,
                    placeholder = stringResource(R.string.manage_expense_cost_placeholder),
                    onValueChange = onChangeCost,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = {
                            focusManager.clearFocus()
                            softwareKeyboardController?.hide()
                            if (uiState.canSubmit) {
                                onDone()
                            }
                        }
                    )
                )
            }
            ActionLayout(
                icon = {
                    Icon(imageVector = Icons.Rounded.Category, contentDescription = null)
                },
                trailing = {
                    Text(text = stringResource(uiState.type.stringResourceId()))
                },
                onClick = onToggleIsSelectingOneType
            ) {
                Text(text = stringResource(R.string.manage_expense_type_placeholder))
            }
        }
    }
}

@Composable
private fun ManageExpenseScreenTopAppBar(
    canDelete: Boolean = false,
    canSubmit: Boolean = false,
    onCancel: () -> Unit = {},
    onDelete: () -> Unit = {},
    onDone: () -> Unit = {}
) {
    SmallTopAppBar(
        title = {},
        navigationIcon = {
            IconButton(onClick = onCancel) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            if (canDelete) {
                IconButton(onClick = onDelete) {
                    Icon(
                        imageVector = Icons.Rounded.DeleteForever,
                        contentDescription = null
                    )
                }
            }
            FilledTonalButton(
                enabled = canSubmit,
                onClick = {
                    if (canSubmit) {
                        onDone()
                    }
                }
            ) {
                Text(text = stringResource(R.string.manage_expense_done_button))
            }
        }
    )
}

@Preview
@Composable
private fun ManageExpenseScreenPreview() {
    FluffyChainsawTheme {
        ManageExpenseScreen()
    }
}