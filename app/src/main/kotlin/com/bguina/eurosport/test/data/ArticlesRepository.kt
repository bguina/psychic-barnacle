package com.bguina.eurosport.test.data

import com.bguina.eurosport.test.domain.IArticlesRepository
import com.bguina.eurosport.test.domain.model.Article
import javax.inject.Inject

class ArticlesRepository @Inject constructor(
    private val articlesDataSource: IArticlesDataSource,
) : IArticlesRepository {
    override suspend fun listArticles(): List<Article> = articlesDataSource.listArticles()
}
