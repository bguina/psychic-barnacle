package com.bguina.eurosport.test.data

import com.bguina.eurosport.test.data.model.StoryEntity
import com.bguina.eurosport.test.data.model.VideoEntity
import com.bguina.eurosport.test.domain.IArticlesRepository
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
import org.hamcrest.CoreMatchers
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Test

class ArticlesRepositoryTests {
    @MockK
    private lateinit var articlesDataSource: IArticlesDataSource

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
    fun `articles should be a mix of stories and videos`() {
        // Given
        val stories = listOf(
            StoryEntity(),
            StoryEntity(),
            StoryEntity(),
        )
        val videos = listOf(
            VideoEntity(),
            VideoEntity(),
            VideoEntity(),
        )
        coEvery { articlesDataSource.listStories() } returns stories
        coEvery { articlesDataSource.listVideos() } returns videos

        // When
        val subject = ArticlesRepository(
            articlesDataSource = articlesDataSource,
        )
        runTest {
            val results = subject.listArticles()

            MatcherAssert.assertThat(results[0], instanceOf(Article.Story::class.java))
            MatcherAssert.assertThat(results[1], instanceOf(Article.Video::class.java))
            MatcherAssert.assertThat(results[2], instanceOf(Article.Story::class.java))
            MatcherAssert.assertThat(results[3], instanceOf(Article.Video::class.java))
            MatcherAssert.assertThat(results[4], instanceOf(Article.Story::class.java))
            MatcherAssert.assertThat(results[5], instanceOf(Article.Video::class.java))
        }
    }
}
