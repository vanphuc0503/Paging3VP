package com.vanphuc.pagingdata3vp.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.vanphuc.pagingdata3vp.data.datasource.local.NewsDao
import com.vanphuc.pagingdata3vp.data.datasource.remote.NewsApi
import com.vanphuc.pagingdata3vp.data.model.News
import com.vanphuc.pagingdata3vp.data.network.NetworkResponse
import com.vanphuc.pagingdata3vp.ui.adapter.NewsPagingDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDao: NewsDao
) : NewsRepository {

    override suspend fun getNews(
        q: String?,
        page: Int?
    ): Flow<PagingData<News>> {
        return Pager(
            config = PagingConfig(
                pageSize = NETWORK_PAGE_SIZE
            ),
            pagingSourceFactory = {
                NewsPagingDataSource (newsDao = newsDao) {
                    val data = newsApi.getNews(q, it)
                    (data as? NetworkResponse.Success)?.body?.articles ?: mutableListOf()
                }
            }
        ).flow
    }

    companion object {
        const val NETWORK_PAGE_SIZE = 1
    }
}