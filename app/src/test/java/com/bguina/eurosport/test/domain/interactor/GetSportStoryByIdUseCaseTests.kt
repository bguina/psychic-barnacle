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
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetSportStoryByIdUseCaseTests {

    @MockK
    private lateinit var articlesRepository: IArticlesRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `it should return the article returned by the repository`() {
        val article = Article.Story(id = 42)
        coEvery { articlesRepository.getStoryArticleById(article.id) } returns article

        val subject = GetSportStoryByIdUseCase(
            articlesRepository = articlesRepository,
        )
        runTest {
            val result = subject.invoke(article.id)

            assertThat(result, `is`(article))
        }
    }

    @Test
    fun `if article is null it should throw NoSuchElementException`() {
        val article = Article.Story(id = 42)
        coEvery { articlesRepository.getStoryArticleById(article.id) } returns null

        val subject = GetSportStoryByIdUseCase(
            articlesRepository = articlesRepository,
        )
        runTest {
            val result = runCatching { subject.invoke(article.id) }

            assertThat(result.exceptionOrNull(), instanceOf(NoSuchElementException::class.java))
        }
    }
}
