package com.bguina.eurosport.test.data

import com.bguina.eurosport.test.data.model.StoryEntity
import com.bguina.eurosport.test.data.model.VideoEntity
import com.bguina.eurosport.test.domain.IArticlesRepository
import com.bguina.eurosport.test.domain.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ArticlesRepository @Inject constructor(
    private val articlesDataSource: IArticlesDataSource,
) : IArticlesRepository {

    override suspend fun listArticlesByDateAsc(): List<Article> = withContext(Dispatchers.IO) {
        val stories = async { articlesDataSource.listStories().map { it.toDomain() } }
        val videos = async { articlesDataSource.listVideos().map { it.toDomain() } }
        (stories.await() + videos.await())
            .sortedWith(compareBy { it.date })
    }

    override suspend fun getStoryArticleById(
        id: Long,
    ): Article.Story? = withContext(Dispatchers.IO) {
        articlesDataSource.listStories().singleOrNull { it.id == id }?.toDomain()
    }

    override suspend fun getVideoById(id: Long): Article.Video?  = withContext(Dispatchers.IO) {
        articlesDataSource.listVideos().singleOrNull { it.id == id }?.toDomain()
    }

    private fun StoryEntity.toDomain(): Article.Story = Article.Story(
        id = this.id ?: throw NoSuchFieldException("id"),
        date = this.date ?: throw NoSuchFieldException("date"),
        sport = this.sport ?: throw NoSuchFieldException("sport"),
        title = this.title ?: throw NoSuchFieldException("title"),
        content = this.content ?: throw NoSuchFieldException("content"),
        imageUrl = this.imageUrl ?: throw NoSuchFieldException("imageUrl"),
        author = this.author ?: throw NoSuchFieldException("author"),
    )

    private fun VideoEntity.toDomain(): Article.Video = Article.Video(
        id = this.id ?: throw NoSuchFieldException("id"),
        date = this.date ?: throw NoSuchFieldException("date"),
        sport = this.sport ?: throw NoSuchFieldException("sport"),
        title = this.title ?: throw NoSuchFieldException("title"),
        videoUrl = this.url ?: throw NoSuchFieldException("url"),
        viewCount = this.viewCount ?: throw NoSuchFieldException("viewCount"),
    )
}
