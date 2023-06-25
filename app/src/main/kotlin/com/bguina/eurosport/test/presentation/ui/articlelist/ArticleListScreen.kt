package com.bguina.eurosport.test.presentation.ui.articlelist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bguina.eurosport.test.domain.model.Article

@Composable
fun ArticleListScreen(
    viewModel: ArticleListViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
    ) {
        items(state.articles) { article ->
            when (article) {
                is Article.Story -> {
                    StoryArticleCard(article = article)
                }

                is Article.Video -> {
                    VideoArticleCard(article = article)
                }
            }
        }
    }
}

@Composable
fun StoryArticleCard(
    article: Article.Story
) {
    // TODO("coil image")
}

@Composable
fun VideoArticleCard(
    article: Article.Video
) {
    // TODO("video player")
}
