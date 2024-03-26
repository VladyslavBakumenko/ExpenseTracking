package com.example.expensetracking.presentation.secondScreen

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.expensetracking.data.local.model.ExpenseDBModel
import com.example.expensetracking.domain.usecases.AddExpenseUseCase
import com.example.expensetracking.presentation.secondScreen.SecondScreenStore.Intent
import com.example.expensetracking.presentation.secondScreen.SecondScreenStore.State
import com.example.expensetracking.presentation.secondScreen.SecondScreenStore.Label

import kotlinx.coroutines.launch
import javax.inject.Inject

internal interface SecondScreenStore : Store<Intent, State, Label> {

    sealed interface Intent {

        data object ClickBack : Intent

        data object ExpenseCreateButtonClicked : Intent
    }

    data class State(
        val expenseDBModel: ExpenseDBModel? = null,
    ) {
        sealed interface SecondScreenState {
            data object Initial : SecondScreenState
        }
    }

    sealed interface Label {

        data object ClickBack : Label
    }
}

internal class SecondStoreFactory @Inject constructor(
    private val storeFactory: StoreFactory,
    private val addExpenseUseCase: AddExpenseUseCase,
) {

    fun create(expenseDBModel: ExpenseDBModel): SecondScreenStore =
        object : SecondScreenStore, Store<Intent, State, Label> by storeFactory.create(
            name = "SecondScreenStore",
            initialState = State(),
            bootstrapper = BootstrapperImpl(expenseDBModel),
            executorFactory = ::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
        data object ExpenseCreated : Action
    }

    private sealed interface Msg {
        data object ExpenseCreated : Msg
    }

    private inner class BootstrapperImpl(
        private val expenseDBModel: ExpenseDBModel
    ) : CoroutineBootstrapper<Action>() {
        override fun invoke() {
            scope.launch {
                addExpenseUseCase(expenseDBModel)
                dispatch(Action.ExpenseCreated)
            }
        }
    }

    private inner class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
            when (intent) {
                Intent.ClickBack -> {
                    publish(Label.ClickBack)
                }

                Intent.ExpenseCreateButtonClicked -> {

                }
            }
        }

        override fun executeAction(action: Action, getState: () -> State) {
            when (action) {
                is Action.ExpenseCreated -> {
                    dispatch(Msg.ExpenseCreated)
                }
            }
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(msg: Msg): State = when (msg) {
            Msg.ExpenseCreated -> TODO()
        }
    }
}