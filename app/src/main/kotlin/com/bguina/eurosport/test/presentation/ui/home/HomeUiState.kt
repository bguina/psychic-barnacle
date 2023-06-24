package com.bguina.eurosport.test.presentation.ui.home

import com.bguina.eurosport.test.domain.model.Article

data class HomeUiState(
    val articles: List<Article> = emptyList(),
)
