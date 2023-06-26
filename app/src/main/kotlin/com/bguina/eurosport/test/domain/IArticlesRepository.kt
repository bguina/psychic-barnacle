package com.bguina.eurosport.test.domain

import com.bguina.eurosport.test.domain.model.Article

interface IArticlesRepository {

    suspend fun listArticlesByDateAsc(): List<Article>

    suspend fun getStoryArticleById(
        id: Long,
    ): Article.Story?

    suspend fun getVideoById(
        id: Long,
    ): Article.Video?
}
