package com.bguina.eurosport.test.data

import com.bguina.eurosport.test.domain.model.Article

interface IArticlesDataSource {
    suspend fun listArticles() : List<Article>
}
