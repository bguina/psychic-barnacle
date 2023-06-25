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
        val stories = listOf(
            StoryEntity(date = 1, title="", content = "", imageUrl = "", author = ""),
            StoryEntity(date = 3, title="", content = "", imageUrl = "", author = ""),
            StoryEntity(date = 2, title="", content = "", imageUrl = "", author = ""),
        )
        val videos = listOf(
            VideoEntity(date = 5, title="", url = "", viewCount = 0),
            VideoEntity(date = 3, title="", url = "", viewCount = 0),
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
