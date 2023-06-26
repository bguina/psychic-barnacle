package com.bguina.eurosport.test.presentation.ui.storydetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bguina.eurosport.test.domain.interactor.GetSportStoryByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoryDetailsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getSportStoryByIdUseCase: GetSportStoryByIdUseCase,
) : ViewModel() {
    val uiState: MutableStateFlow<StoryDetailsUiState> = MutableStateFlow(StoryDetailsUiState())

    init {
        viewModelScope.launch(Dispatchers.Main) {
            val story = getSportStoryByIdUseCase(requireNotNull(savedStateHandle["storyId"]))

            uiState.update { it.copy(story = story) }
        }
    }

}
