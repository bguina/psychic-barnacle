package com.bguina.eurosport.test.presentation.ui.articlelist

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import com.bguina.eurosport.test.R
import com.bguina.eurosport.test.domain.model.Article
import com.bguina.eurosport.test.presentation.ext.timeAgo
import com.bguina.eurosport.test.presentation.ui.theme.EurosportTheme
import java.util.Date

@Preview
@Composable
fun Preview() {
    EurosportTheme {
        Layout(
            ArticleListUiState(
                listOf(
                    Article.Story(
                        date = System.currentTimeMillis() - 360000,
                        sport = "Football",
                        title = "Title which is a little too long to be displayed on a single line",
                        author = "Henry",
                    ),
                )
            )
        )
    }
}

@Composable
fun ArticleListScreen(
    viewModel: ArticleListViewModel = hiltViewModel(),
    onArticleClicked: (Article) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Layout(
        state = state,
        onArticleClicked = onArticleClicked
    )
}

@Composable
private fun Layout(
    state: ArticleListUiState,
    onArticleClicked: (Article) -> Unit = {}
) {
    Scaffold(
        topBar = {
            Text(
                modifier = Modifier
                    .background(Color(0xFF141B4D))
                    .padding(12.dp)
                    .fillMaxWidth(),
                text = stringResource(R.string.articlelist_title),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                color = Color.White,
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
                .fillMaxSize(),
        ) {
            items(state.articles) { article ->
                when (article) {
                    is Article.Story ->
                        StoryArticleCard(
                            article = article,
                            onArticleClicked = onArticleClicked,
                        )

                    is Article.Video ->
                        VideoArticleCard(
                            article = article,
                            onArticleClicked = onArticleClicked,
                        )
                }
            }
        }
    }
}

@Composable
fun StoryArticleCard(
    article: Article.Story,
    onArticleClicked: (Article) -> Unit,
) {
    ArticleCard(
        sport = article.sport,
        title = article.title,
        articleInfo = stringResource(R.string.article_headline_author_timeago, article.author, Date(article.date).timeAgo()),
        onArticleClicked = { onArticleClicked(article) },
    ) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = article.imageUrl,
            contentDescription = article.title,
            contentScale = ContentScale.FillWidth,
        )
    }
}

@Composable
fun VideoArticleCard(
    article: Article.Video,
    onArticleClicked: (Article) -> Unit,
) {
    ArticleCard(
        sport = article.sport,
        title = article.title,
        articleInfo = stringResource(R.string.article_headline_video_viewcount, article.viewCount),
        onArticleClicked = { onArticleClicked(article) },
    ) {
        Box(contentAlignment = Alignment.Center) {
            AsyncImage(
                modifier = Modifier.fillMaxSize(),
                model = article.videoUrl,
                contentDescription = article.title,
                imageLoader = ImageLoader.Builder(LocalContext.current)
                    .components { add(VideoFrameDecoder.Factory()) }
                    .build(),
                contentScale = ContentScale.FillWidth,
            )
            Icon(
                modifier = Modifier
                    .scale(2f)
                    .clickable { onArticleClicked(article) },
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play",
                tint = Color.White,
            )
        }
    }
}
