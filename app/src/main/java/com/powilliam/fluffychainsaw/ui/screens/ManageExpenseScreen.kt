package com.powilliam.fluffychainsaw.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.DeleteForever
import androidx.compose.material.icons.rounded.Done
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.powilliam.fluffychainsaw.ui.composables.ContainedTextField
import com.powilliam.fluffychainsaw.ui.theme.FluffyChainsawTheme
import com.powilliam.fluffychainsaw.R
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import com.powilliam.fluffychainsaw.ui.composables.Option
import com.powilliam.fluffychainsaw.ui.composables.SingleOptionSelection
import com.powilliam.fluffychainsaw.ui.constants.ManageExpenseViewMode
import com.powilliam.fluffychainsaw.ui.viewmodels.ManageExpenseUiState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ManageExpenseScreen(
    modifier: Modifier = Modifier,
    uiState: ManageExpenseUiState = ManageExpenseUiState(),
    onChangeName: (String) -> Unit = {},
    onChangeCost: (String) -> Unit = {},
    onChangeType: (ExpenseType) -> Unit = {},
    onDelete: () -> Unit = {},
    onCancel: () -> Unit = {},
    onDone: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    val canSubmit = listOf(uiState.name, uiState.cost).all { field -> field.isNotEmpty() }

    Scaffold(
        topBar = {
            ManageExpenseScreenTopAppBar(
                canSubmit = canSubmit,
                canDelete = uiState.viewMode == ManageExpenseViewMode.ViewingOne,
                onCancel = onCancel,
                onDelete = onDelete,
                onDone = onDone
            )
        }
    ) {
        Column(modifier.padding(16.dp)) {
            ContainedTextField(
                value = uiState.name,
                placeholder = stringResource(R.string.manage_expense_name_placeholder),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                onValueChange = onChangeName
            )
            Spacer(modifier.height(16.dp))
            ContainedTextField(
                value = uiState.cost,
                placeholder = stringResource(R.string.manage_expense_cost_placeholder),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        if (canSubmit) {
                            onDone()
                        }
                    }
                ),
                onValueChange = onChangeCost
            )
            Spacer(modifier.height(16.dp))
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
                    ),
                )
            ) { option ->
                onChangeType(option.value)
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
                Icon(imageVector = Icons.Rounded.Close, contentDescription = null)
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
            IconButton(
                enabled = canSubmit,
                onClick = {
                    if (canSubmit) {
                        onDone()
                    }
                }
            ) {
                Icon(imageVector = Icons.Rounded.Done, contentDescription = null)
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