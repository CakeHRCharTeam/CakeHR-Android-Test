package com.sage.cahekr.cakehrandroidapp.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sage.cahekr.cakehrandroidapp.utils.CoroutineRule
import com.sage.cahekr.cakehrandroidapp.domain.DomainFixtures
import com.sage.cahekr.cakehrandroidapp.domain.usecase.GetFilmListUseCase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.amshove.kluent.shouldBeEqualTo
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var coroutinesTestRule = CoroutineRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @MockK
    internal lateinit var mockFilmListUseCase: GetFilmListUseCase

    private lateinit var mainViewModel: MainViewModel

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        mainViewModel = MainViewModel(mockFilmListUseCase)
    }

    @Test
    fun `verify state when GetFilmListUseCase returns empty list`() {
        coEvery { mockFilmListUseCase.execute() } returns GetFilmListUseCase.Result.Success(
            emptyList()
        )

        mainViewModel.loadData()

        mainViewModel.stateLiveData.value shouldBeEqualTo MainViewModel.ViewState(
            isLoading = false,
            isError = true,
            films = listOf()
        )
    }

    @Test
    fun `verify state when GetFilmListUseCase returns non-empty list`() {
        val film = DomainFixtures.getFilm()
        val films = listOf(film)
        coEvery { mockFilmListUseCase.execute() } returns GetFilmListUseCase.Result.Success(films)

        mainViewModel.loadData()

        mainViewModel.stateLiveData.value shouldBeEqualTo MainViewModel.ViewState(
            isLoading = false,
            isError = false,
            films = films
        )
    }
}
