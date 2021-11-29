package com.powilliam.fluffychainsaw.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.usecases.GetAllExpensesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ExpensesUiState(val expenses: List<Expense> = emptyList())

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
}