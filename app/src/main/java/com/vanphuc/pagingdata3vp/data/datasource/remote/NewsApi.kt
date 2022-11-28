package com.vanphuc.pagingdata3vp.data.datasource.remote

import com.vanphuc.pagingdata3vp.data.model.BaseModel
import com.vanphuc.pagingdata3vp.data.model.News
import com.vanphuc.pagingdata3vp.data.network.GenericResponse
import com.vanphuc.pagingdata3vp.data.network.NetworkResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("everything")
    suspend fun getNews(
        @Query("q") q: String? = null,
        @Query("page") page: Int? = null
    ): GenericResponse<BaseModel<List<News>>>
}