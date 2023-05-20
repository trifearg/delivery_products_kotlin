package ru.app.fastestdelivery.util.ui.inputState

sealed interface InputState<out T> {

    fun isError() = this is Error

    fun isData() = this is Data


    data class Data<out T>(val value: String) : InputState<T>

    sealed interface Error : InputState<Nothing> {

        object EmptyInput: Error

    }

}
