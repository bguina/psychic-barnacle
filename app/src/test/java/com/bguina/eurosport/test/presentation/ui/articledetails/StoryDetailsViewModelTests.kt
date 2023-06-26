package com.bguina.eurosport.test.presentation.ui.articledetails

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.bguina.eurosport.test.domain.interactor.GetSportStoryByIdUseCase
import com.bguina.eurosport.test.domain.model.Article
import com.bguina.eurosport.test.presentation.ui.storydetails.StoryDetailsViewModel
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

class StoryDetailsViewModelTests {

    @MockK
    private lateinit var getSportStoryByIdUseCase: GetSportStoryByIdUseCase

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
    fun `it should display the article from storyId argument`() {
        val story = Article.Story(id = 42)
        val savedStateHandle = SavedStateHandle().apply {
            set("storyId", story.id)
        }
        coEvery { getSportStoryByIdUseCase.invoke(story.id) } returns story

        val subject = StoryDetailsViewModel(
            savedStateHandle = savedStateHandle,
            getSportStoryByIdUseCase = getSportStoryByIdUseCase,
        )

        runTest {
            subject.uiState.test {
                val state = awaitItem()

                assertThat(state.story, `is`(story))
            }
        }
    }
}
