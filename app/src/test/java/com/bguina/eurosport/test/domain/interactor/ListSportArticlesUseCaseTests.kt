package com.bguina.eurosport.test.domain.interactor

import com.bguina.eurosport.test.domain.IArticlesRepository
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

class ListSportArticlesUseCaseTests {
    @MockK
    private lateinit var articlesRepository: IArticlesRepository

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
    fun `articles should be sorted by date`() {
        // Given
        val articles = listOf(
            Article(date = 2),
            Article(date = 1),
            Article(date = 3),
        )
        coEvery { articlesRepository.listArticles() } returns articles

        // When
        val subject = ListSportArticlesUseCase(
            articlesRepository = articlesRepository,
        )
        runTest {
            val results = subject.invoke()

            assertThat(results[0], `is`(articles[1]))
            assertThat(results[1], `is`(articles[0]))
            assertThat(results[2], `is`(articles[2]))
        }
    }
}
