package com.bguina.eurosport.test.domain.interactor

import com.bguina.eurosport.test.domain.IArticlesRepository
import com.bguina.eurosport.test.domain.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSportStoryByIdUseCase @Inject constructor(
    private val articlesRepository: IArticlesRepository,
) {
    suspend operator fun invoke(
        id: Long,
    ): Article.Story = withContext(Dispatchers.Main) {
        articlesRepository.getStoryArticleById(id = id)
            ?: throw NoSuchElementException("no story with id $id")
    }
}
