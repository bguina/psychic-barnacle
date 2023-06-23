package com.bguina.eurosport.test.domain.interactor

import com.bguina.eurosport.test.domain.IArticlesRepository
import com.bguina.eurosport.test.domain.model.Article
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat

class ListSportArticlesUseCaseTests {
    @MockK
    private lateinit var articlesRepository: IArticlesRepository

    fun `articles should be sorted by date`() {
        // Given
        val articles = listOf(
            Article(date=2),
            Article(date=1),
            Article(date=3),
        )
        coEvery { articlesRepository.listArticles() } returns articles

        // When
        val subject = ListSportArticlesUseCase(
            articlesRepository = articlesRepository,
        )

        assertThat(results[0], `is`(articles[0]))
    }
}
