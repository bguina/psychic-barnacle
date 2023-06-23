package com.bguina.eurosport.test.domain

import com.bguina.eurosport.test.domain.model.Article

interface IArticlesRepository {

    suspend fun listArticles() : List<Article>
}
