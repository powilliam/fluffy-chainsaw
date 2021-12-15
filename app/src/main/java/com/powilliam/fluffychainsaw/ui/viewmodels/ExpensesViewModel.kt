package com.powilliam.fluffychainsaw.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powilliam.fluffychainsaw.R
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.repositories.ExpensesRepository
import com.powilliam.fluffychainsaw.data.repositories.SettingsRepository
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
    val daysUntilMonthEnding = daysUntil(monthEndingInUtcMilliseconds)
    val canDisplayDaysUntilMonthEnding = monthEndingInUtcMilliseconds > 0
    val daysUntilMonthEndingStringResourceId = when {
        !canDisplayDaysUntilMonthEnding -> R.string.expenses_screen_month_ending_is_not_defined
        daysUntilMonthEnding == 0 && canDisplayDaysUntilMonthEnding -> R.string.expenses_screen_today_is_the_month_ending
        else -> R.string.expenses_screen_days_until_month_ending
    }
}

@HiltViewModel
class ExpensesViewModel @Inject constructor(
    private val expensesRepository: ExpensesRepository,
    private val settingsRepository: SettingsRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<ExpensesUiState> = MutableStateFlow(ExpensesUiState())
    val uiState: StateFlow<ExpensesUiState> = _uiState

    init {
        viewModelScope.launch {
            val expensesFlow = expensesRepository.getAllExpenses()
            val settingsFlow = settingsRepository.getSettings()

            expensesFlow.combine(settingsFlow) { expenses, settings ->
                ExpensesUiState(
                    expenses = expenses,
                    monthEndingInUtcMilliseconds = settings.monthEndingInUtcMilliseconds
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
            settingsRepository.updateSettings(monthEndingInUtcMilliseconds = monthEndingInUtcMilliseconds)
        }
    }
}