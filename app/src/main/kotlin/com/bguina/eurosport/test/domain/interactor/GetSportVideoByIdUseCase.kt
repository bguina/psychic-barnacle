package com.bguina.eurosport.test.domain.interactor

import com.bguina.eurosport.test.domain.IArticlesRepository
import com.bguina.eurosport.test.domain.model.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetSportVideoByIdUseCase @Inject constructor(
    private val articlesRepository: IArticlesRepository,
) {
    suspend operator fun invoke(
        id: Long,
    ): Article.Video = withContext(Dispatchers.Main) {
        articlesRepository.getVideoById(id = id)
            ?: throw NoSuchElementException("no story with id $id")
    }
}
