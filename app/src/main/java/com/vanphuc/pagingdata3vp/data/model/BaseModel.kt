package com.vanphuc.pagingdata3vp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class BaseModel<T>(
    @get:Json(name = "status")
    var status: String? = null,
    @get:Json(name = "totalResults")
    var totalResults: Int? = null,
    @get:Json(name = "articles")
    var articles: T? = null
)