package com.example.expensetracking.presentation.mainScreen

import com.arkivanov.mvikotlin.core.store.Reducer
import com.arkivanov.mvikotlin.core.store.Store
import com.arkivanov.mvikotlin.core.store.StoreFactory
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineBootstrapper
import com.arkivanov.mvikotlin.extensions.coroutines.CoroutineExecutor
import com.example.expensetracking.presentation.mainScreen.MainScreenStore.Intent
import com.example.expensetracking.presentation.mainScreen.MainScreenStore.Label
import com.example.expensetracking.presentation.mainScreen.MainScreenStore.State

internal interface MainScreenStore : Store<Intent, State, Label> {

    sealed interface Intent {
    }

    data class State(val todo: Unit)

    sealed interface Label {
    }
}

internal class DetailsStoreFactory(
    private val storeFactory: StoreFactory
) {

    fun create(): MainScreenStore =
        object : MainScreenStore, Store<Intent, State, Label> by storeFactory.create(
            name = "MainScreenStore",
            initialState = State(Unit),
            bootstrapper = BootstrapperImpl(),
            executorFactory = DetailsStoreFactory::ExecutorImpl,
            reducer = ReducerImpl
        ) {}

    private sealed interface Action {
    }

    private sealed interface Msg {
    }

    private class BootstrapperImpl : CoroutineBootstrapper<Action>() {
        override fun invoke() {
        }
    }

    private class ExecutorImpl : CoroutineExecutor<Intent, Action, State, Msg, Label>() {
        override fun executeIntent(intent: Intent, getState: () -> State) {
        }

        override fun executeAction(action: Action, getState: () -> State) {
        }
    }

    private object ReducerImpl : Reducer<State, Msg> {
        override fun State.reduce(message: Msg): State = State(Unit)
    }
}