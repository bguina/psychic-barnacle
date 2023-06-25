package com.bguina.eurosport.test.presentation.ui.articlelist

import android.view.ViewGroup
import android.widget.FrameLayout
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
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.VideoFrameDecoder
import com.bguina.eurosport.test.R
import com.bguina.eurosport.test.domain.model.Article
import com.github.marlonlom.utilities.timeago.TimeAgo
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout
import com.google.android.exoplayer2.ui.StyledPlayerView

@Composable
fun ArticleListScreen(
    viewModel: ArticleListViewModel = hiltViewModel(),
    onArticleClicked: (Article) -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

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
                color = Color.White
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
        mediaInfo = stringResource(
            R.string.article_headline_author_timeago,
            article.author,
            TimeAgo.using(article.date)
        ),
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
        mediaInfo = stringResource(R.string.article_headline_video_viewcount, article.viewCount),
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

@Composable
fun VideoPlayer(
    modifier: Modifier = Modifier,
    videoUrl: String,
) {
    val context = LocalContext.current

    // create our player
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem: MediaItem = MediaItem.fromUri(videoUrl)
            setMediaItem(mediaItem)
            prepare()
        }
    }

    // player view
    DisposableEffect(
        AndroidView(
            modifier = modifier,
            factory = {
                StyledPlayerView(context).apply {
                    player = exoPlayer
                    layoutParams = FrameLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                    )
                    resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FILL
                }
            }
        )
    ) {
        onDispose {
            exoPlayer.release()
        }
    }
}
