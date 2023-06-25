package com.bguina.eurosport.test.domain.interactor

import com.bguina.eurosport.test.domain.IArticlesRepository
import com.bguina.eurosport.test.domain.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListSportArticlesUseCase @Inject constructor(
    private val articlesRepository: IArticlesRepository,
) {
    suspend operator fun invoke(): List<Article> = withContext(Dispatchers.Main) {
        articlesRepository.listArticlesByDateAsc()
            .alternatingBetweenStoriesAndVideos()
    }

    private fun List<Article>.alternatingBetweenStoriesAndVideos(): List<Article> {
        val stories = filterIsInstance<Article.Story>()
        val videos = filterIsInstance<Article.Video>()

        return stories.zip(videos) { story, video->
            listOf(story, video)
        }.flatten()
    }
}
