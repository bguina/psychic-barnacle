package com.bguina.eurosport.test.presentation.ui.videoplayer

import com.bguina.eurosport.test.domain.model.Article

data class VideoPlayerUiState(
    val video: Article.Video? = null,
)
