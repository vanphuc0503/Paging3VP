package com.vanphuc.pagingdata3vp.ui.adapter

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vanphuc.pagingdata3vp.data.datasource.local.NewsDao
import com.vanphuc.pagingdata3vp.data.model.News
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsPagingDataSource<T : Any>(
    private val newsDao: NewsDao,
    private val apiResult: suspend (page: Int) -> List<T>,
) : PagingSource<Int, T>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        val page = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = apiResult(page)

            (response as? List<News>)?.let {
                withContext(Dispatchers.IO) {
                    newsDao.insertAllNews(it)
                }
            }
            LoadResult.Page(
                data = response,
                prevKey = if (page == STARTING_PAGE_INDEX) null else page.minus(1),
                nextKey = if (response.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }
}