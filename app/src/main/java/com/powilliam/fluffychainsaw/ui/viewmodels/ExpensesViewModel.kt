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
    val query: String = ""
) {
    val filteredExpenses = expenses.filter { expense ->
        expense.name.contains(
            query,
            ignoreCase = false
        ) or expense.name.contentEquals(query, ignoreCase = false)
    }
    val totalCost = expenses.fold(0F) { totalCost, expense -> totalCost + expense.cost }
}

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val getAllExpensesUseCase: GetAllExpensesUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<ExpensesUiState> = MutableStateFlow(ExpensesUiState())
    val uiState: StateFlow<ExpensesUiState> = _uiState

    init {
        viewModelScope.launch {
            getAllExpensesUseCase.execute()
                .collect { expenses ->
                    _uiState.emit(_uiState.value.copy(expenses = expenses))
                }
        }
    }

    fun onSearch(newValue: String) {
        viewModelScope.launch {
            with(_uiState.value) {
                _uiState.emit(this.copy(query = newValue))
            }
        }
    }
}