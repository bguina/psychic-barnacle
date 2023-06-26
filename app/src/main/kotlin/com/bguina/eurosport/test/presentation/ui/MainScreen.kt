package com.bguina.eurosport.test.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.bguina.eurosport.test.domain.model.Article
import com.bguina.eurosport.test.presentation.ui.articlelist.ArticleListScreen
import com.bguina.eurosport.test.presentation.ui.storydetails.StoryDetailsScreen
import com.bguina.eurosport.test.presentation.ui.theme.EurosportTheme

@Preview
@Composable
fun Preview() {
    EurosportTheme { MainScreen() }
}


@Composable
fun MainScreen(
) {
    Scaffold(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
    ) { padding ->
        Surface(modifier = Modifier.padding(padding)) {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "articles") {
                composable("articles") {
                    ArticleListScreen(
                        onArticleClicked = { article ->
                            when (article) {
                                is Article.Story -> {
                                    navController.navigate("story_details/${article.id}")
                                }

                                is Article.Video -> {

                                }
                            }
                        }
                    )
                }
                composable(
                    route = "story_details/{storyId}",
                    arguments = listOf(
                        navArgument("storyId") {
                            type = NavType.LongType
                        }
                    )) {
                    StoryDetailsScreen(
                        onBackPressed = navController::popBackStack,
                    )
                }
            }
        }
    }
}
