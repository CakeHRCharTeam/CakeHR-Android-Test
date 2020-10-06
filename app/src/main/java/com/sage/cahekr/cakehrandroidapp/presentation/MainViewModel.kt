package com.sage.cahekr.cakehrandroidapp.presentation

import androidx.lifecycle.viewModelScope
import com.sage.cahekr.cakehrandroidapp.domain.model.FilmDomainModel
import com.sage.cahekr.cakehrandroidapp.domain.usecase.GetFilmListUseCase
import com.sage.cahekr.cakehrandroidapp.presentation.base.BaseAction
import com.sage.cahekr.cakehrandroidapp.presentation.base.BaseViewModel
import com.sage.cahekr.cakehrandroidapp.presentation.base.BaseViewState
import kotlinx.coroutines.launch

internal class MainViewModel(
    private val getFilmListUseCase: GetFilmListUseCase
) : BaseViewModel<MainViewModel.ViewState, MainViewModel.Action>(ViewState()) {

    override fun onLoadData() {
        getFilmList()
    }

    override fun onReduceState(viewAction: Action) = when (viewAction) {
        is Action.FilmListLoadingSuccess -> state.copy(
            isLoading = false,
            isError = false,
            films = viewAction.films
        )
        is Action.FilmListLoadingFailure -> state.copy(
            isLoading = false,
            isError = true,
            films = listOf()
        )
    }

    private fun getFilmList() {
        viewModelScope.launch {
            getFilmListUseCase.execute().also { result ->
                val action = when (result) {
                    is GetFilmListUseCase.Result.Success ->
                        if (result.data.isEmpty()) {
                            Action.FilmListLoadingFailure
                        } else {
                            Action.FilmListLoadingSuccess(result.data)
                        }

                    is GetFilmListUseCase.Result.Error ->
                        Action.FilmListLoadingFailure
                }
                sendAction(action)
            }
        }
    }

    internal data class ViewState(
        val isLoading: Boolean = true,
        val isError: Boolean = false,
        val films: List<FilmDomainModel> = listOf()
    ) : BaseViewState

    internal sealed class Action : BaseAction {
        class FilmListLoadingSuccess(val films: List<FilmDomainModel>) : Action()
        object FilmListLoadingFailure : Action()
    }
}
