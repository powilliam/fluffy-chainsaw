package com.powilliam.fluffychainsaw.ui.bottomsheets

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
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

@Composable
fun ManageExpenseBottomSheet(
    modifier: Modifier = Modifier,
    onCancel: () -> Unit = {},
    onDone: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current

    Surface(modifier.fillMaxWidth()) {
        Column(modifier.padding(16.dp)) {
            ContainedTextField(
                value = "",
                placeholder = stringResource(R.string.manage_expense_name_placeholder),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                onValueChange = {}
            )
            Spacer(modifier.height(16.dp))
            ContainedTextField(
                value = "",
                placeholder = stringResource(R.string.manage_expense_cost_placeholder),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onDone = { onDone() }
                ),
                onValueChange = {}
            )
            Spacer(modifier.height(16.dp))
            SingleOptionSelection(
                options = listOf(
                    Option(
                        label = stringResource(R.string.expense_type_fixed),
                        value = ExpenseType.Fixed
                    ),
                    Option(
                        label = stringResource(R.string.expense_type_variable),
                        value = ExpenseType.Variable
                    )
                )
            ) {}
            Spacer(modifier.height(16.dp))
            Row(
                modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                TextButton(onClick = onCancel) {
                    Text(text = stringResource(R.string.manage_expense_cancel_button))
                }
                Spacer(modifier.width(8.dp))
                FilledTonalButton(onClick = onDone) {
                    Text(text = stringResource(R.string.manage_expense_done_button))
                }
            }
        }
    }
}

@Preview
@Composable
private fun ManageExpenseBottomSheetPreview() {
    FluffyChainsawTheme {
        ManageExpenseBottomSheet()
    }
}