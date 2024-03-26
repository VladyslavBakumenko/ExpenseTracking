package com.example.expensetracking.presentation.mainScreen

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.expensetracking.data.local.model.ExpenseDBModel
import com.example.expensetracking.data.network.dto.CryptocurrencyPriceData
import com.example.expensetracking.domain.usecases.GetAllExpensesUseCase
import com.example.expensetracking.domain.usecases.GetBtcPriceUseCase
import com.example.expensetracking.presentation.mainScreen.MainScreenStore.Intent
import com.example.expensetracking.presentation.mainScreen.MainScreenStore.Label
import com.example.expensetracking.presentation.mainScreen.MainScreenStore.State
import kotlinx.coroutines.launch
import javax.inject.Inject

interface MainScreenStore : Store<Intent, State, Label> {

    sealed interface Intent {
        data object ClickDeposit : Intent
        data object ClickAddTransaction : Intent
    }

    data class State(
        val test: Boolean? = null
       // val expenseItems: List<ExpenseDBModel>
    ) {


        sealed interface BitcoinPriceState {
            data object Initial : BitcoinPriceState
            data object Loading : BitcoinPriceState
            data object Error : BitcoinPriceState
            data class Loaded(val cryptocurrencyPriceData: CryptocurrencyPriceData) :
                BitcoinPriceState
        }
    }

    sealed interface Label {
        data object ClickDeposit : Label
        data object ClickAddTransaction : Label
    }
}

class DetailsStoreFactory @Inject constructor(
    private val getAllExpensesUseCase: GetAllExpensesUseCase,
    private val getBtcPriceUseCase: GetBtcPriceUseCase,
    private val storeFactory: StoreFactory
) {

    fun create(): MainScreenStore =
        object : MainScreenStore, Store<Intent, State, Label> by storeFactory.create(
            name = "MainScreenStore",
            initialState = State(),
            bootstrapper = BootstrapperImpl(),
            executorFactory = DetailsStoreFactory::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data class ExpenseListLoaded(val expenseList: List<ExpenseDBModel>):Action
    }

    private sealed interface Msg {
        data class ExpenseListLoaded(val expenseList: List<ExpenseDBModel>): Msg
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                getAllExpensesUseCase().collect {
                    dispatch(Action.ExpenseListLoaded(it))
                }
            }
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when(action) {
                is Action.ExpenseListLoaded -> {
                    val cities = action.expenseList
                    dispatch(Msg.ExpenseListLoaded(cities))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(message: Msg): State = State()
    }
}