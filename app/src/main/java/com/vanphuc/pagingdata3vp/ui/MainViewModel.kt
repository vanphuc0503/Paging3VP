package com.vanphuc.pagingdata3vp.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.vanphuc.pagingdata3vp.data.model.News
import com.vanphuc.pagingdata3vp.data.repository.NewsRepository
import com.vanphuc.pagingdata3vp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val newsRepository: NewsRepository
) : BaseViewModel() {

    var userItemsUiStates: Flow<PagingData<News>>? = null

    fun getNews(keyword: String) {
        viewModelScopeExceptionHandler.launch {
            userItemsUiStates = newsRepository.getNews(keyword, null).cachedIn(viewModelScope)
        }
    }
}