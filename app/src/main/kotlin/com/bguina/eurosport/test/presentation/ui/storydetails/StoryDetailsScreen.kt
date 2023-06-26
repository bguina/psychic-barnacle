package com.bguina.eurosport.test.presentation.ui.storydetails

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.bguina.eurosport.test.R
import com.bguina.eurosport.test.domain.model.Article
import com.bguina.eurosport.test.presentation.ext.timeAgo
import com.bguina.eurosport.test.presentation.ui.common.SportBanner
import com.bguina.eurosport.test.presentation.ui.theme.EurosportTheme
import java.util.Date

@Preview
@Composable
fun Preview() {
    EurosportTheme {
        Layout(
            StoryDetailsUiState(
                Article.Story(
                    date = System.currentTimeMillis() - 360000,
                    sport = "Football",
                    title = "Title which is a little too long to be displayed on a single line",
                    author = "Henry",
                    content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer accumsan lacus velit, ac venenatis sapien auctor eu. Aliquam metus velit, fermentum quis nibh sed, tincidunt consequat elit. Donec malesuada tellus metus, nec vestibulum dui volutpat porta. Suspendisse at lacus consequat, varius elit cursus, dictum orci. Sed sit amet purus vitae eros sodales facilisis at posuere ipsum. Pellentesque interdum sagittis imperdiet. Etiam efficitur id nulla nec iaculis. Maecenas consectetur ligula id vulputate volutpat."
                ),
            )
        )
    }
}

@Composable
fun StoryDetailsScreen(
    viewModel: StoryDetailsViewModel = hiltViewModel(),
    onBackPressed: () -> Unit,
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Layout(
        state = state,
        onBackPressed = onBackPressed,
    )
}

@Composable
private fun Layout(
    state: StoryDetailsUiState,
    onBackPressed: () -> Unit = {},
) {
    val story = state.story

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
    ) {
        if (null != story) {
            StoryBanner(story, onBackPressed)
            StoryDetails(story)
        } else {
            CircularProgressIndicator()
        }
    }
}

@Composable
private fun StoryBanner(
    story: Article.Story,
    onBackPressed: () -> Unit
) {
    SportBanner(sport = story.sport) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = story.imageUrl,
            contentDescription = story.title,
            contentScale = ContentScale.FillWidth,
        )
        Row(modifier = Modifier.padding(12.dp)) {
            Icon(
                modifier = Modifier
                    .scale(1.5f)
                    .clickable(onClick = onBackPressed),
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "back",
                tint = Color.White,
            )
            Spacer(modifier = Modifier.weight(1f, true))
            Icon(
                modifier = Modifier.scale(1.5f),
                imageVector = Icons.Default.Share,
                contentDescription = "share",
                tint = Color.White,
            )
        }
    }
}

@Composable
private fun StoryDetails(story: Article.Story) {
    Column(modifier = Modifier.padding(16.dp, 0.dp)) {
        Text(
            text = story.title,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = stringResource(R.string.story_details_author, story.author),
            style = MaterialTheme.typography.bodyMedium,
        )
        Text(
            text = Date(story.date).timeAgo(),
            style = MaterialTheme.typography.bodySmall,
            color=Color.Gray,
        )
        Text(
            modifier = Modifier.padding(0.dp, 12.dp),
            text = story.content,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}
