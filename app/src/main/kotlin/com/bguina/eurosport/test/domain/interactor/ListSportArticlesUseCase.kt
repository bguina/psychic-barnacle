package com.bguina.eurosport.test.domain.interactor

import com.bguina.eurosport.test.domain.IArticlesRepository
import com.bguina.eurosport.test.domain.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListSportArticlesUseCase @Inject constructor(
    private val articlesRepository: IArticlesRepository,
) {
    suspend operator fun invoke(): List<Article> = withContext(Dispatchers.IO) {
        articlesRepository.listArticlesByDateAsc()
            .alternatingBetweenStoriesAndVideos()
    }

    private fun List<Article>.alternatingBetweenStoriesAndVideos(): List<Article> {
        var awaitStory = true

        return fold(emptyList()) { acc, article ->
            if ((awaitStory && article is Article.Story) ||
                (!awaitStory && article is Article.Video)
            ) {
                awaitStory = !awaitStory
                acc + article
            } else acc
        }
    }
}
