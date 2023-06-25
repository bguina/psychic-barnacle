package com.bguina.eurosport.test.presentation.ui.articlelist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bguina.eurosport.test.domain.interactor.ListSportArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleListViewModel @Inject constructor(
    private val listSportArticlesUseCase: ListSportArticlesUseCase,
) : ViewModel() {
    val uiState: MutableStateFlow<ArticleListUiState> = MutableStateFlow(ArticleListUiState())

    init {
        viewModelScope.launch(Dispatchers.Main) {
            val articles = listSportArticlesUseCase()

            uiState.update { it.copy(articles = articles) }
        }
    }

}
