package com.bguina.eurosport.test.data

import com.bguina.eurosport.test.data.model.StoryEntity
import com.bguina.eurosport.test.data.model.VideoEntity
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

class ArticlesRepositoryTests {

    @MockK
    private lateinit var articlesDataSource: IArticlesDataSource

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
    fun `listArticlesByDateAsc should list articles sorted by date asc`() {
        // Given
        val dummyStory = StoryEntity(
            id = 0,
            date = 1,
            sport = "",
            title = "",
            content = "",
            imageUrl = "",
            author = ""
        )
        val dummyVideo = VideoEntity(
            id = 0,
            date = 5,
            sport = "",
            title = "",
            url = "",
            viewCount = 0
        )
        val stories = listOf(
            dummyStory.copy(date = 1),
            dummyStory.copy(date = 3),
            dummyStory.copy(date = 2),
        )
        val videos = listOf(
            dummyVideo.copy(date = 5),
            dummyVideo.copy(date = 3),
        )
        coEvery { articlesDataSource.listStories() } returns stories
        coEvery { articlesDataSource.listVideos() } returns videos

        // When
        val subject = ArticlesRepository(
            articlesDataSource = articlesDataSource,
        )
        runTest {
            val results = subject.listArticlesByDateAsc()

            assertThat(results, `is`(results.sortedBy { it.date }))
        }
    }
}
