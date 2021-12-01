package com.powilliam.fluffychainsaw.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.usecases.GetAllExpensesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ExpensesUiState(
    val expenses: List<Expense> = emptyList(),
    val filteredExpenses: List<Expense> = emptyList(),
    val query: String = ""
)

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val getAllExpensesUseCase: GetAllExpensesUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<ExpensesUiState> = MutableStateFlow(ExpensesUiState())
    val uiState: StateFlow<ExpensesUiState> = _uiState

    init {
        viewModelScope.launch {
            getAllExpensesUseCase.execute().collect { expenses ->
                _uiState.emit(ExpensesUiState(expenses))
            }
        }
    }

    fun onSearch(newValue: String) {
        viewModelScope.launch {
            with(_uiState.value) {
                _uiState.emit(
                    this.copy(
                        query = newValue,
                        filteredExpenses = filterExpensesThatMatchesWithQuery(newValue)
                    )
                )
            }
        }
    }

    private fun ExpensesUiState.filterExpensesThatMatchesWithQuery(query: String): List<Expense> {
        return if (query.isEmpty()) {
            emptyList()
        } else {
            expenses.filter { expense ->
                expense.name.contains(
                    query,
                    ignoreCase = false
                ) or expense.name.contentEquals(
                    query,
                    ignoreCase = false
                )
            }
        }
    }
}