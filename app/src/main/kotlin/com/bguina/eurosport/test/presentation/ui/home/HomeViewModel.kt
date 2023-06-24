package com.bguina.eurosport.test.presentation.ui.home

import androidx.lifecycle.ViewModel
import com.bguina.eurosport.test.domain.interactor.ListSportArticlesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val listSportArticlesUseCase: ListSportArticlesUseCase,
) : ViewModel() {

}
