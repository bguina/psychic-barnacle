package com.bguina.eurosport.test.data

import com.bguina.eurosport.test.data.model.StoryEntity
import com.bguina.eurosport.test.data.model.VideoEntity
import com.bguina.eurosport.test.domain.IArticlesRepository
import com.bguina.eurosport.test.domain.model.Article
import java.lang.Integer.min
import javax.inject.Inject

class ArticlesRepository @Inject constructor(
    private val articlesDataSource: IArticlesDataSource,
) : IArticlesRepository {

    override suspend fun listArticles(): List<Article> {
        val stories = articlesDataSource.listStories()
        val videos = articlesDataSource.listVideos()
        val maxArticles = min(stories.size, videos.size) * 2 + 1

        return (0..maxArticles)
            .map { listOf(stories.getOrNull(it)?.toDomain(), videos.getOrNull(it)?.toDomain()) }
            .flatten()
            .filterNotNull()
    }

    private fun StoryEntity.toDomain(): Article.Story = Article.Story(
        date = this.date ?: throw NoSuchFieldException("date"),
        content = this.content ?: throw NoSuchFieldException("content"),
        imageUrl = this.imageUrl ?: throw NoSuchFieldException("imageUrl"),
        author = this.author ?: throw NoSuchFieldException("author"),
    )

    private fun VideoEntity.toDomain(): Article.Video = Article.Video(
        date = this.date ?: throw NoSuchFieldException("date"),
        videoUrl = this.url ?: throw NoSuchFieldException("url"),
        viewCount = this.viewCount ?: throw NoSuchFieldException("viewCount"),
    )
}
