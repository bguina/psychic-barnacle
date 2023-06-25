package com.bguina.eurosport.test.domain.interactor

import com.bguina.eurosport.test.domain.IArticlesRepository
import com.bguina.eurosport.test.domain.model.Article
import javax.inject.Inject

class ListSportArticlesUseCase @Inject constructor(
    private val articlesRepository: IArticlesRepository,
) {
    suspend operator fun invoke() : List<Article> = articlesRepository.listArticlesByDateAsc()
        .sortedWith(compareBy { it.date })
}
