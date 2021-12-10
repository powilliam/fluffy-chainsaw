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
    ) {}
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