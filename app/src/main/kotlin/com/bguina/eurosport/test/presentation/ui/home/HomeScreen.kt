package com.bguina.eurosport.test.presentation.ui.home

import android.util.Log
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bguina.eurosport.test.domain.model.Article

object HomeScreen {

    @Composable
    operator fun invoke(
        viewModel: HomeViewModel = hiltViewModel(),
    ) {
        val state by viewModel.uiState.collectAsStateWithLifecycle()

        LaunchedEffect(Unit) {
            viewModel.loadArticles()
        }
        Layout(
            articles = state.articles,
        )
    }

    @Composable
    fun Layout(
        articles: List<Article>
    ) {
        LazyColumn {
            items(articles) { article ->
                when (article) {
                    is Article.Video -> {
                        // TODO("video player")
                    }

                    is Article.Story -> {
                        // TODO("coil image")
                    }
                }
            }
        }
    }
}
