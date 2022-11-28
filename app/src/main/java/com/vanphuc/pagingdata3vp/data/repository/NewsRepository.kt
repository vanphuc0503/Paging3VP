package com.vanphuc.pagingdata3vp.data.repository

import androidx.paging.PagingData
import com.vanphuc.pagingdata3vp.data.model.BaseModel
import com.vanphuc.pagingdata3vp.data.model.News
import com.vanphuc.pagingdata3vp.data.network.GenericResponse
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(q: String?, page: Int? = null): Flow<PagingData<News>>
}