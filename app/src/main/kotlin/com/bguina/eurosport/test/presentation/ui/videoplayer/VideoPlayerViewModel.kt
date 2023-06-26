package com.bguina.eurosport.test.presentation.ui.videoplayer

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bguina.eurosport.test.domain.interactor.GetSportVideoByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VideoPlayerViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getSportVideoByIdUseCase: GetSportVideoByIdUseCase,
) : ViewModel() {
    val uiState: MutableStateFlow<VideoPlayerUiState> = MutableStateFlow(VideoPlayerUiState())

    init {
        viewModelScope.launch(Dispatchers.Main) {
            val video = getSportVideoByIdUseCase(requireNotNull(savedStateHandle["videoId"]))

            uiState.update { it.copy(video = video) }
        }
    }
}
