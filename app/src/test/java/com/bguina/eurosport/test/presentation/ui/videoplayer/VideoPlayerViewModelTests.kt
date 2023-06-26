package com.bguina.eurosport.test.presentation.ui.videoplayer

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.bguina.eurosport.test.domain.interactor.GetSportVideoByIdUseCase
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

class VideoPlayerViewModelTests {

    @MockK
    private lateinit var getSportVideoByIdUseCase: GetSportVideoByIdUseCase

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
    fun `it should load the video from videoId argument`() {
        val video = Article.Video(id = 42)
        val savedStateHandle = SavedStateHandle().apply {
            set("videoId", video.id)
        }
        coEvery { getSportVideoByIdUseCase.invoke(video.id) } returns video

        val subject = VideoPlayerViewModel(
            savedStateHandle = savedStateHandle,
            getSportVideoByIdUseCase = getSportVideoByIdUseCase,
        )

        runTest {
            subject.uiState.test {
                val state = awaitItem()

                assertThat(state.video, `is`(video))
            }
        }
    }
}
