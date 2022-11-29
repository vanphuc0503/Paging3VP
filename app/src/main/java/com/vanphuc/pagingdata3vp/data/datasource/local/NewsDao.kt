package com.vanphuc.pagingdata3vp.data.datasource.local

import androidx.room.*
import com.vanphuc.pagingdata3vp.data.model.News

@Dao
interface NewsDao {
    @Query("SELECT * FROM News")
    fun getAll(): List<News>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllNews(news: List<News>)

    @Delete
    fun delete(news: News)
}