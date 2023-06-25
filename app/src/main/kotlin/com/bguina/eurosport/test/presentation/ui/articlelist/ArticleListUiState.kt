package com.bguina.eurosport.test.presentation.ui.articlelist

import com.bguina.eurosport.test.domain.model.Article

data class ArticleListUiState(
    val articles: List<Article> = emptyList(),
)
