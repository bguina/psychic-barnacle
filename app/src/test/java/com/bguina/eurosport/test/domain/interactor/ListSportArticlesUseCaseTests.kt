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

class ListSportArticlesUseCaseTests {

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
    fun `resulting stories and videos should remain sorted by date asc`() {
        coEvery { articlesRepository.listArticlesByDateAsc() } returns listOf(
            Article.Story(date = 1),
            Article.Story(date = 2),
            Article.Video(date = 3),
            Article.Video(date = 4),
            Article.Story(date = 5),
            Article.Video(date = 6),
        )

        val subject = ListSportArticlesUseCase(
            articlesRepository = articlesRepository,
        )
        runTest {
            val results = subject.invoke()

            val resultingStories = results.filterIsInstance<Article.Story>()
            val resultingVideos = results.filterIsInstance<Article.Video>()
            assertThat(resultingStories, `is`(resultingStories.sortedBy { it.date }))
            assertThat(resultingVideos, `is`(resultingVideos.sortedBy { it.date }))
        }
    }

    @Test
    fun `articles should alternate between stories and videos`() {
        coEvery { articlesRepository.listArticlesByDateAsc() } returns listOf(
            Article.Story(date = 1),
            Article.Story(date = 2),
            Article.Video(date = 3),
            Article.Video(date = 4),
            Article.Story(date = 5),
        )

        val subject = ListSportArticlesUseCase(
            articlesRepository = articlesRepository,
        )
        runTest {
            val results = subject.invoke()

            var expectStory = results.firstOrNull() is Article.Story
            results.forEach { result->
                if (expectStory) {
                    assertThat(result, instanceOf(Article.Story::class.java))
                } else {
                    assertThat(result, instanceOf(Article.Video::class.java))
                }
                expectStory = !expectStory
            }
        }
    }
}
