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
import com.example.expensetracking.presentation.mainScreen.MainScreenStore.State
import com.example.expensetracking.presentation.mainScreen.MainScreenStore.Label
import kotlinx.coroutines.launch

import javax.inject.Inject

interface MainScreenStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data object DepositClicked : Intent
    }

    data class State(
        val cityItems: List<ExpenseItem>
    ) {

        data class ExpenseItem(val expenseDBModel: ExpenseDBModel)

        sealed interface BtcState {

            data object Initial : BtcState

            data object Loading : BtcState

            data object Error : BtcState

            data class Loaded(val cryptocurrencyPriceData: CryptocurrencyPriceData) : BtcState
        }
    }

    sealed interface Label {

        data object DepositClicked : Label

        data class ExpensesItemClicked(val expenseDBModel: ExpenseDBModel) : Label
    }
}

class FavouriteStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val getBtcPriceUseCase: GetBtcPriceUseCase,
    private val getAllExpensesUseCase: GetAllExpensesUseCase
) {

    fun create(): MainScreenStore =
        object : MainScreenStore, Store<Intent, State, Label> by storeFactory.create(
            name = "FavouriteStore",
            initialState = State(listOf()),
            bootstrapper = BootstrapperImpl(),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {

        data class BtcPriceLoaded(val cryptocurrencyPriceData: CryptocurrencyPriceData) : Action
    }

    private sealed interface Msg {

        data class ExpenseListLoaded(val expenseList: List<ExpenseDBModel>) : Msg

        data class BtcPriceLoaded(val cryptocurrencyPriceData: CryptocurrencyPriceData) : Msg

        data object BtcPriceLoading : Msg

        data object BtcPriceLoadedError : Msg
    }

    private inner class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                getBtcPriceUseCase()
                getBtcPriceUseCase().collect {
                    dispatch(Action.BtcPriceLoaded(it))
                }
            }
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                is Intent.DepositClicked -> {
                    publish(Label.DepositClicked)
                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.BtcPriceLoaded -> {
                    dispatch(Msg.BtcPriceLoaded(action.cryptocurrencyPriceData))
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when (msg) {
            is Msg.ExpenseListLoaded -> {
                copy(
                    cityItems = msg.expenseList .map {
                        State.ExpenseItem(expenseDBModel = it)
                    }
                )
            }

            is Msg.BtcPriceLoaded -> TODO()
            Msg.BtcPriceLoadedError -> TODO()
            Msg.BtcPriceLoading -> TODO()
        }
    }
}