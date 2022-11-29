package com.vanphuc.pagingdata3vp.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "News")
data class News(
    @PrimaryKey val title: String,
    @ColumnInfo val source: Source? = null,
    @ColumnInfo val author: String? = null,
    @ColumnInfo val description: String? = null,
    @ColumnInfo val url: String? = null,
    @ColumnInfo val urlToImage: String? = null,
    @ColumnInfo val publishedAt: String? = null,
    @ColumnInfo val content: String? = null,
)

data class Source(
    val id: String? = null,
    val name: String? = null
)