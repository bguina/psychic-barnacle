package com.bguina.eurosport.test.presentation.ui.home

import app.cash.turbine.test
import com.bguina.eurosport.test.domain.interactor.ListSportArticlesUseCase
import com.bguina.eurosport.test.domain.model.Article
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeViewModelTests {

    @MockK
    private lateinit var listSportArticlesUseCase: ListSportArticlesUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        MockKAnnotations.init(this, relaxUnitFun = true)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `it should display the articles`() {
        // Given
        val articles = listOf(
            Article.Story(),
            Article.Story(),
            Article.Story(),
        )
        coEvery { listSportArticlesUseCase.invoke() } returns articles

        // When
        val subject = HomeViewModel(
            listSportArticlesUseCase = listSportArticlesUseCase,
        )
        subject.loadArticles()

        // Then
        runTest {
            subject.uiState.test {
                val state = awaitItem()

                assertThat(state.articles, `is`(articles))
            }
        }
    }
}
