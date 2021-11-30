package com.powilliam.fluffychainsaw.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.powilliam.fluffychainsaw.data.entities.Expense
import com.powilliam.fluffychainsaw.data.entities.ExpenseType
import com.powilliam.fluffychainsaw.data.usecases.DeleteOneExpenseUseCase
import com.powilliam.fluffychainsaw.data.usecases.GetOneExpenseUseCase
import com.powilliam.fluffychainsaw.data.usecases.InsertManyExpensesUseCase
import com.powilliam.fluffychainsaw.data.usecases.UpdateOneExpenseUseCase
import com.powilliam.fluffychainsaw.ui.constants.ManageExpenseViewMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class ManageExpenseUiState(
    val expenseId: Long = -1,
    val name: String = "",
    val cost: String = "",
    val type: ExpenseType = ExpenseType.Variable,
    val viewMode: ManageExpenseViewMode = ManageExpenseViewMode.InsertingOne
)

@HiltViewModel
class ManageExpenseViewModel @Inject constructor(
    private val getOneExpenseUseCase: GetOneExpenseUseCase,
    private val updateOneExpenseUseCase: UpdateOneExpenseUseCase,
    private val insertManyExpensesUseCase: InsertManyExpensesUseCase,
    private val deleteOneExpenseUseCase: DeleteOneExpenseUseCase
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
                    is ManageExpenseViewMode.ViewingOne -> onUpdate()
                }
            }
        }
    }

    fun onDelete() {
        viewModelScope.launch {
            try {
                deleteOneExpenseUseCase.execute(_uiState.value.expenseId)
            } catch (exception: Exception) {
            }
        }
    }

    fun onSetUiStateBasedOnViewMode(viewMode: String?, expenseId: Long?) {
        when {
            isViewingOne(viewMode, expenseId) -> viewModelScope.launch {
                val expense = getOneExpenseUseCase.execute(expenseId!!)
                _uiState.emit(
                    ManageExpenseUiState(
                        expenseId = expense.expenseId,
                        name = expense.name,
                        cost = "${expense.cost}",
                        type = expense.type,
                        viewMode = ManageExpenseViewMode.ViewingOne
                    )
                )
            }
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

    private suspend fun ManageExpenseUiState.onUpdate() {
        try {
            updateOneExpenseUseCase.execute(
                id = this.expenseId,
                name = this.name,
                cost = this.cost.toFloat(),
                type = this.type
            )
        } catch (exception: Exception) {
        }
    }
}