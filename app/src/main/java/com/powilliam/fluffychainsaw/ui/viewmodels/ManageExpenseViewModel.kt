package com.powilliam.fluffychainsaw.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import com.powilliam.fluffychainsaw.data.usecases.InsertManyExpensesUseCase
import com.powilliam.fluffychainsaw.ui.constants.ManageExpenseViewMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ManageExpenseUiState(
    val name: String = "",
    val cost: String = "",
    val type: ExpenseType = ExpenseType.Variable,
    val viewMode: ManageExpenseViewMode = ManageExpenseViewMode.InsertingOne
)

@HiltViewModel
class ManageExpenseViewModel @Inject constructor(
    private val insertManyExpensesUseCase: InsertManyExpensesUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<ManageExpenseUiState> = MutableStateFlow(
        ManageExpenseUiState()
    )
    val uiState: StateFlow<ManageExpenseUiState> = _uiState

    fun onInsertOrUpdate() {
        viewModelScope.launch {
            with(_uiState.value) {
                when (this.viewMode) {
                    is ManageExpenseViewMode.InsertingOne -> onInsert()
                    else -> {}
                }
            }
        }
    }

    fun onSetUiStateBasedOnViewMode(viewMode: String?, expenseId: Long?) {
        when {
            // TODO: Get from database and set values to state
            isViewingOne(viewMode, expenseId) -> {}
            else -> {}
        }
    }

    fun onChangeName(newValue: String) {
        viewModelScope.launch {
            _uiState.emit(_uiState.value.copy(name = newValue))
        }
    }

    fun onChangeCost(newValue: String) {
        viewModelScope.launch {
            _uiState.emit(_uiState.value.copy(cost = newValue))
        }
    }

    fun onChangeType(newValue: ExpenseType) {
        viewModelScope.launch {
            _uiState.emit(_uiState.value.copy(type = newValue))
        }
    }

    private fun isViewingOne(viewMode: String?, expenseId: Long?): Boolean {
        return viewMode == ManageExpenseViewMode.ViewingOne.alias && expenseId != null
    }

    private suspend fun ManageExpenseUiState.onInsert() {
        try {
            val expense = Expense(
                name = this.name,
                cost = this.cost.toFloat(),
                type = this.type
            )
            insertManyExpensesUseCase.execute(expense)
        } catch (exception: Exception) {
        }
    }
}