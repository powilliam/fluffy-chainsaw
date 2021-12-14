package com.powilliam.fluffychainsaw.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.usecases.GetAllExpensesUseCase
import com.powilliam.fluffychainsaw.data.usecases.GetMonthEndingFromSettingsDataStoreUseCase
import com.powilliam.fluffychainsaw.data.usecases.UpdateMonthEndingFromSettingsDataStoreUseCase
import com.powilliam.fluffychainsaw.ui.utils.daysUntil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ExpensesUiState(
    val expenses: List<Expense> = emptyList(),
    val query: String = "",
    val monthEndingInUtcMilliseconds: Long = 0,
) {
    val filteredExpenses = expenses.filter { expense ->
        expense.name.contains(
            query,
            ignoreCase = false
        ) or expense.name.contentEquals(query, ignoreCase = false)
    }
    val totalCost = expenses.fold(0F) { totalCost, expense -> totalCost + expense.cost }
    val canDisplayDaysUntilMonthEnding = monthEndingInUtcMilliseconds > 0
    val daysUntilMonthEnding = daysUntil(monthEndingInUtcMilliseconds)
}

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val getAllExpensesUseCase: GetAllExpensesUseCase,
    private val getMonthEndingFromSettingsDataStoreUseCase: GetMonthEndingFromSettingsDataStoreUseCase,
    private val updateMonthEndingFromSettingsDataStoreUseCase: UpdateMonthEndingFromSettingsDataStoreUseCase
) : ViewModel() {
    private val _uiState: MutableStateFlow<ExpensesUiState> = MutableStateFlow(ExpensesUiState())
    val uiState: StateFlow<ExpensesUiState> = _uiState

    init {
        viewModelScope.launch {
            val expensesFlow = getAllExpensesUseCase.execute()
            val monthEndingFlow = getMonthEndingFromSettingsDataStoreUseCase.execute()

            expensesFlow.combine(monthEndingFlow) { expenses, monthEndingInUtcMilliseconds ->
                ExpensesUiState(
                    expenses = expenses,
                    monthEndingInUtcMilliseconds = monthEndingInUtcMilliseconds
                )
            }.collect(_uiState::emit)
        }
    }

    fun onSearch(newValue: String) {
        viewModelScope.launch {
            with(_uiState.value) {
                _uiState.emit(this.copy(query = newValue))
            }
        }
    }

    fun onSelectMonthEnding(monthEndingInUtcMilliseconds: Long) {
        viewModelScope.launch {
            updateMonthEndingFromSettingsDataStoreUseCase.execute(monthEndingInUtcMilliseconds)
        }
    }
}